/* ═══════════════════════════════════════════════════════════════════
   DASHBOARD — lógica exclusiva de dashboard.html
   Depende de: app.js (ENTITIES, apiFetch, extractItems, esc)
═══════════════════════════════════════════════════════════════════ */

/* ── Estado global ──────────────────────────────────────────────── */
let currentEntity   = null;
let editingId       = null;
let currentItems    = [];
let bsModal         = null;
let bsDeleteModal   = null;
let deleteTargetId  = null;

/* ── Verificar sesión al cargar ─────────────────────────────────── */
(function checkSession() {
  const raw = sessionStorage.getItem('sessionUser');
  if (!raw) {
    window.location.href = '/';
    return;
  }
  const user = JSON.parse(raw);
  document.getElementById('hdrName').textContent = user.nombre || user.id;
  initDashboard();
  loadEntity('proyectos');
})();

/* ── Logout ─────────────────────────────────────────────────────── */
function logout() {
  sessionStorage.removeItem('sessionUser');
  window.location.href = '/';
}

/* ═══════════════════════════════════════════════════════════════════
   INICIALIZACIÓN DEL DASHBOARD (GSAP)
═══════════════════════════════════════════════════════════════════ */
function initDashboard() {
  gsap.registerPlugin(ScrollTrigger);

  gsap.from('.app-navbar', {
    y: -40, opacity: 0, duration: 0.5, ease: 'power3.out',
  });

  buildSidebar();

  gsap.from('.nav-item-link', {
    x: -20, duration: 0.4,
    stagger: 0.07, delay: 0.1,
    ease: 'power2.out',
  });

  gsap.from('.app-footer', {
    y: 20, opacity: 0, duration: 0.5, delay: 0.3, ease: 'power2.out',
  });
}

/* ═══════════════════════════════════════════════════════════════════
   SIDEBAR
═══════════════════════════════════════════════════════════════════ */
function buildSidebar() {
  document.getElementById('sidebarItems').innerHTML =
    Object.entries(ENTITIES).map(([key, cfg]) => `
      <a class="nav-item-link" id="nav-${key}" href="#"
         onclick="loadEntity('${key}'); return false;">
        <i class="bi ${cfg.icon}"></i>${cfg.label}
      </a>`
    ).join('');
}

/* ═══════════════════════════════════════════════════════════════════
   CARGAR ENTIDAD — GET /api/{path}?size=200
═══════════════════════════════════════════════════════════════════ */
async function loadEntity(key) {
  currentEntity = key;
  const cfg = ENTITIES[key];

  document.querySelectorAll('.nav-item-link').forEach(n => n.classList.remove('active'));
  document.getElementById(`nav-${key}`)?.classList.add('active');

  ScrollTrigger.getAll().forEach(t => t.kill());

  document.getElementById('mainContent').innerHTML = `
    <div class="d-flex justify-content-between align-items-center mb-3" id="pageHead">
      <div>
        <div class="page-title"><i class="bi ${cfg.icon} me-2 text-primary"></i>${cfg.label}</div>
        <div class="page-count" id="pageCount"></div>
      </div>
      <button class="btn btn-primary btn-sm fw-semibold" onclick="openCreate()">
        <i class="bi bi-plus-lg me-1"></i>Nuevo
      </button>
    </div>
    <div class="search-bar-wrap mb-3">
      <div class="input-group input-group-sm">
        <span class="input-group-text search-addon">
          <i class="bi bi-search"></i>
        </span>
        <input type="text" class="form-control search-input" id="searchInput"
               placeholder="Buscar en ${cfg.label}..."
               oninput="filterTable(this.value)">
        <button class="btn btn-outline-secondary" type="button" onclick="loadEntity('${key}')" title="Recargar">
          <i class="bi bi-arrow-clockwise"></i>
        </button>
      </div>
    </div>
    <div class="card content-card" id="dataCard">
      <div class="state-box">
        <div class="d-flex justify-content-center mb-3">
          <div class="spin-ring"></div>
        </div>
        Cargando datos...
      </div>
    </div>`;

  gsap.from('#pageHead', { y: -14, opacity: 0, duration: 0.4, ease: 'power2.out' });

  try {
    const data = await apiFetch(`/${cfg.path}?size=200`);
    currentItems = extractItems(data);
    renderTable(key, currentItems);
  } catch (e) {
    document.getElementById('dataCard').innerHTML = `
      <div class="state-box text-danger">
        <i class="bi bi-exclamation-circle"></i>
        Error al cargar datos<br>
        <small class="text-muted">${esc(e.message)}</small>
      </div>`;
  }
}

/* ═══════════════════════════════════════════════════════════════════
   RENDERIZAR TABLA + ANIMACIONES GSAP + SCROLLTRIGGER
═══════════════════════════════════════════════════════════════════ */
function renderTable(key, items) {
  const cfg = ENTITIES[key];

  document.getElementById('pageCount').textContent =
    `${items.length} registro${items.length !== 1 ? 's' : ''}`;

  if (!items.length) {
    document.getElementById('dataCard').innerHTML = `
      <div class="state-box">
        <i class="bi bi-inbox"></i>
        Sin registros. Usa <strong>Nuevo</strong> para agregar.
      </div>`;
    gsap.from('#dataCard', { y: 20, opacity: 0, duration: 0.4, ease: 'power2.out' });
    return;
  }

  const thead = cfg.cols.map(c => `<th>${c.h}</th>`).join('') + '<th>Acciones</th>';
  const tbody = items.map((item, idx) => `
    <tr class="tbl-row">
      ${cfg.cols.map(c => `<td>${fmtCell(item[c.k], c)}</td>`).join('')}
      <td>
        <div class="d-flex gap-2">
          <button class="btn btn-outline-primary btn-action" title="Editar" onclick="openEdit(${idx})">
            <i class="bi bi-pencil"></i>
          </button>
          <button class="btn btn-outline-danger btn-action" title="Eliminar" onclick="askDelete('${esc(String(item.id))}')">
            <i class="bi bi-trash"></i>
          </button>
        </div>
      </td>
    </tr>`).join('');

  document.getElementById('dataCard').innerHTML = `
    <div class="table-responsive">
      <table class="table app-table mb-0">
        <thead><tr>${thead}</tr></thead>
        <tbody>${tbody}</tbody>
      </table>
    </div>`;

  gsap.from('#dataCard', { y: 24, opacity: 0, duration: 0.45, ease: 'power3.out' });

  gsap.fromTo('.tbl-row',
    { opacity: 0, y: 14 },
    {
      opacity: 1, y: 0,
      duration: 0.3,
      stagger: 0.04,
      delay: 0.1,
      ease: 'power2.out',
      onComplete: () => {
        document.querySelectorAll('.tbl-row').forEach(row => {
          ScrollTrigger.create({
            trigger: row,
            scroller: '#mainContent',
            start: 'top 95%',
            once: true,
            onEnter: () => gsap.to(row, { opacity: 1, y: 0, duration: 0.25 }),
          });
        });
      },
    }
  );
}

/* ── Formatea el valor de una celda ─────────────────────────────── */
function fmtCell(val, col) {
  if (val == null || val === '') return '<span class="text-muted small">—</span>';
  const s = String(val);
  if (!col.badge) return esc(s);
  const v = s.toLowerCase();
  const cls = v.includes('activ')    ? 'badge-green'
            : v.includes('inactiv')  ? 'badge-red'
            : v.includes('pendient') ? 'badge-blue'
            : 'badge-gray';
  return `<span class="status-badge ${cls}">${esc(s)}</span>`;
}

/* ═══════════════════════════════════════════════════════════════════
   MODAL — CREAR / EDITAR
═══════════════════════════════════════════════════════════════════ */
function openCreate() {
  editingId = null;
  const cfg = ENTITIES[currentEntity];
  document.getElementById('modalTitle').textContent = `Nuevo — ${cfg.label}`;
  buildForm(cfg.fields, {}, false);
  getModal().show();
}

function openEdit(idx) {
  const item = currentItems[idx];
  editingId  = item.id;
  const cfg  = ENTITIES[currentEntity];
  document.getElementById('modalTitle').textContent = `Editar — ${esc(String(item.id))}`;
  buildForm(cfg.fields, item, true);
  getModal().show();
}

function getModal() {
  if (!bsModal) bsModal = new bootstrap.Modal(document.getElementById('crudModal'));
  return bsModal;
}

function buildForm(fields, data, isEdit) {
  document.getElementById('modalBody').innerHTML = fields
    .filter(f => !(isEdit && f.createOnly))
    .map(f => {
      const v   = data[f.k] != null ? String(data[f.k]) : '';
      const req = f.req ? '<span class="text-danger">*</span>' : '';
      const inp = f.type === 'textarea'
        ? `<textarea class="form-control" id="f-${f.k}" rows="3">${esc(v)}</textarea>`
        : `<input type="${f.type || 'text'}" class="form-control" id="f-${f.k}" value="${esc(v)}">`;
      return `
        <div class="mb-3 form-field-item">
          <label class="form-label fw-semibold small">${f.l} ${req}</label>
          ${inp}
        </div>`;
    }).join('');

  gsap.fromTo('.form-field-item',
    { opacity: 0, y: 10 },
    { opacity: 1, y: 0, duration: 0.25, stagger: 0.06, delay: 0.05, ease: 'power2.out' }
  );
}

/* ── Guardar (POST para nuevo, PATCH para editar) ──────────────── */
async function saveRecord() {
  const cfg      = ENTITIES[currentEntity];
  const isEdit   = editingId !== null;
  const body     = {};
  let   firstErr = null;
  let   errCount = 0;

  for (const f of cfg.fields) {
    if (isEdit && f.createOnly) continue;
    const el = document.getElementById(`f-${f.k}`);
    if (!el) continue;
    const v = el.value.trim();
    if (f.req && !v) {
      el.classList.add('is-invalid');
      if (!firstErr) firstErr = el;
      errCount++;
    } else {
      el.classList.remove('is-invalid');
      body[f.k] = v;
    }
  }

  if (errCount > 0) {
    const msg = errCount === 1
      ? 'Hay 1 campo obligatorio sin completar.'
      : `Hay ${errCount} campos obligatorios sin completar.`;
    toast(msg, 'danger');
    firstErr?.focus();
    return;
  }

  const btn = document.getElementById('btnSave');
  btn.disabled = true;
  btn.innerHTML = '<span class="spinner-border spinner-border-sm me-1"></span>Guardando...';

  try {
    if (isEdit) {
      await apiFetch(`/${cfg.path}/${encodeURIComponent(editingId)}`, {
        method: 'PATCH',
        body: JSON.stringify(body),
      });
      toast('Registro actualizado correctamente.', 'success');
    } else {
      await apiFetch(`/${cfg.path}`, {
        method: 'POST',
        body: JSON.stringify(body),
      });
      toast('Registro creado correctamente.', 'success');
    }
    getModal().hide();
    loadEntity(currentEntity);
  } catch (e) {
    toast(`Error: ${e.message}`, 'danger');
  } finally {
    btn.disabled = false;
    btn.innerHTML = '<i class="bi bi-save me-1"></i>Guardar';
  }
}

/* ── Eliminar — abre modal de confirmación ─────────────────────── */
function askDelete(id) {
  deleteTargetId = id;
  document.getElementById('deleteMsg').textContent =
    `El registro con ID "${id}" será eliminado permanentemente.`;

  if (!bsDeleteModal) {
    bsDeleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
    document.getElementById('btnConfirmDelete').addEventListener('click', confirmDelete);
  }
  bsDeleteModal.show();
}

async function confirmDelete() {
  const cfg = ENTITIES[currentEntity];
  const btn = document.getElementById('btnConfirmDelete');
  btn.disabled = true;
  btn.innerHTML = '<span class="spinner-border spinner-border-sm me-1"></span>Eliminando...';

  try {
    await apiFetch(`/${cfg.path}/${encodeURIComponent(deleteTargetId)}`, { method: 'DELETE' });
    bsDeleteModal.hide();
    toast('Registro eliminado correctamente.', 'success');
    loadEntity(currentEntity);
  } catch (e) {
    toast(`Error al eliminar: ${e.message}`, 'danger');
  } finally {
    btn.disabled = false;
    btn.innerHTML = '<i class="bi bi-trash me-1"></i>Eliminar';
  }
}

/* ── Filtrar tabla (búsqueda en cliente) ───────────────────────── */
function filterTable(query) {
  const q = query.toLowerCase().trim();
  const count = document.getElementById('pageCount');

  if (!q) {
    renderTable(currentEntity, currentItems);
    return;
  }

  const filtered = currentItems.filter(item =>
    Object.values(item).some(v => v != null && String(v).toLowerCase().includes(q))
  );

  renderTable(currentEntity, filtered);

  if (count && filtered.length !== currentItems.length) {
    count.textContent = `${filtered.length} de ${currentItems.length} registro${currentItems.length !== 1 ? 's' : ''}`;
  }
}

/* ═══════════════════════════════════════════════════════════════════
   TOASTS
═══════════════════════════════════════════════════════════════════ */
const TOAST_ICONS = {
  success: 'bi-check-circle-fill',
  danger:  'bi-x-circle-fill',
  info:    'bi-info-circle-fill',
  warning: 'bi-exclamation-triangle-fill',
};

function toast(msg, type = 'info') {
  const id   = 'toast-' + Date.now();
  const icon = TOAST_ICONS[type] ?? TOAST_ICONS.info;
  const html = `
    <div id="${id}" class="toast align-items-center text-bg-${type} border-0" role="alert">
      <div class="d-flex">
        <div class="toast-body d-flex align-items-center gap-2">
          <i class="bi ${icon}"></i>${esc(msg)}
        </div>
        <button type="button" class="btn-close btn-close-white me-2 m-auto"
                data-bs-dismiss="toast"></button>
      </div>
    </div>`;

  const box = document.getElementById('toastBox');
  box.insertAdjacentHTML('beforeend', html);

  const el = document.getElementById(id);
  new bootstrap.Toast(el, { delay: 3500, autohide: true }).show();

  gsap.fromTo(el,
    { x: 60, opacity: 0 },
    { x: 0,  opacity: 1, duration: 0.35, ease: 'power3.out' }
  );

  setTimeout(() => {
    gsap.to(el, { x: 60, opacity: 0, duration: 0.3, ease: 'power2.in',
      onComplete: () => el.remove() });
  }, 3200);
}
