/* ═══════════════════════════════════════════════════════════════════
   CONFIGURACIÓN DE ENTIDADES
   ─────────────────────────────────────────────────────────────────
   Cada entidad define:
     path   → ruta en /api  (coincide con @RepositoryRestResource)
     cols   → columnas que muestra la tabla
     fields → campos del formulario crear/editar

   Los key de cols y fields corresponden EXACTAMENTE a los nombres
   de los getters/setters de cada clase Java, tal como los expone
   Jackson y Spring Data REST.
═══════════════════════════════════════════════════════════════════ */
const API = '/api';

const ENTITIES = {

  // ── Proyectos ──────────────────────────────────────────────────
  // Modelo: Proyectos extends Persona
  // Getters relevantes: getId, getNombre, getCorreo,
  //   getTipo_proyecto, getDescripcion_proyecto,
  //   getFechaInicio_proyecto, getFechaFin_proyecto, getEstado_proyecto
  // Setters para POST/PATCH: setNombre, setCorreo, setTipo_proyecto,
  //   setDescripcion_proyecto, setFechaInicio_proyecto,
  //   setFechaFin_proyecto, setEstado_proyecto
  proyectos: {
    label: 'Proyectos',
    icon: 'bi-folder2-open',
    path: 'proyectos',
    cols: [
      { k: 'id',                   h: 'ID'      },
      { k: 'nombre',               h: 'Nombre'  },
      { k: 'correo',               h: 'Correo'  },
      { k: 'tipo_proyecto',        h: 'Tipo'    },
      { k: 'estado_proyecto',      h: 'Estado',      badge: true },
      { k: 'fechaInicio_proyecto', h: 'Inicio'  },
      { k: 'fechaFin_proyecto',    h: 'Fin'     },
    ],
    fields: [
      { k: 'id',                   l: 'ID',           req: true, createOnly: true },
      { k: 'nombre',               l: 'Nombre',        req: true  },
      { k: 'correo',               l: 'Correo',        type: 'email' },
      { k: 'tipo_proyecto',        l: 'Tipo',          req: true  },
      { k: 'descripcion_proyecto', l: 'Descripción',   type: 'textarea' },
      { k: 'fechaInicio_proyecto', l: 'Fecha Inicio',  req: true  },
      { k: 'fechaFin_proyecto',    l: 'Fecha Fin'               },
      { k: 'estado_proyecto',      l: 'Estado',        req: true  },
    ],
  },

  // ── Participantes ──────────────────────────────────────────────
  // Modelo: Participantes extends Persona
  // Getters: getId, getNombre, getCorreo,
  //   getUbicacion_participante, getRol_participante
  // Setters POST/PATCH: setNombre, setCorreo,
  //   setUbicacion_participante, setRol_participante
  participantes: {
    label: 'Participantes',
    icon: 'bi-people',
    path: 'participantes',
    cols: [
      { k: 'id',                    h: 'ID'        },
      { k: 'nombre',                h: 'Nombre'    },
      { k: 'correo',                h: 'Correo'    },
      { k: 'ubicacion_participante',h: 'Ubicación' },
      { k: 'rol_participante',      h: 'Rol',      badge: true },
    ],
    fields: [
      { k: 'id',                    l: 'ID',        req: true, createOnly: true },
      { k: 'nombre',                l: 'Nombre',    req: true },
      { k: 'correo',                l: 'Correo',    type: 'email' },
      { k: 'ubicacion_participante',l: 'Ubicación', req: true },
      { k: 'rol_participante',      l: 'Rol',       req: true },
    ],
  },

  // ── Patrocinios ────────────────────────────────────────────────
  // Modelo: Patrocinios extends Persona
  // Getters: getId, getNombre, getCorreo,
  //   getContacto_patrocinador, getTipo_patrocinador, getAporte_patrocinador
  // Setters POST/PATCH: setNombre, setCorreo,
  //   setContacto_patrocinador, setTipo_patrocinador, setAporte_patrocinador
  patrocinios: {
    label: 'Patrocinios',
    icon: 'bi-handshake',
    path: 'patrocinios',
    cols: [
      { k: 'id',                   h: 'ID'       },
      { k: 'nombre',               h: 'Nombre'   },
      { k: 'correo',               h: 'Correo'   },
      { k: 'contacto_patrocinador',h: 'Contacto' },
      { k: 'tipo_patrocinador',    h: 'Tipo'     },
      { k: 'aporte_patrocinador',  h: 'Aporte'   },
    ],
    fields: [
      { k: 'id',                   l: 'ID',      req: true, createOnly: true },
      { k: 'nombre',               l: 'Nombre',  req: true },
      { k: 'correo',               l: 'Correo',  type: 'email' },
      { k: 'contacto_patrocinador',l: 'Contacto',req: true },
      { k: 'tipo_patrocinador',    l: 'Tipo'              },
      { k: 'aporte_patrocinador',  l: 'Aporte'            },
    ],
  },

  // ── Recursos ───────────────────────────────────────────────────
  // Modelo: Recursos extends RecursoBase
  // RecursoBase tiene: getId, getNombre, getCategoria, getEstado, getUbicacion
  // Recursos agrega: getNombre_delrecurso (= nombre), etc. (alias)
  // Setters POST/PATCH: setNombre, setCategoria, setEstado, setUbicacion
  //   (de RecursoBase, que son los mismos que setNombre_delrecurso, etc.)
  recursos: {
    label: 'Recursos',
    icon: 'bi-boxes',
    path: 'recursos',
    cols: [
      { k: 'id',        h: 'ID'        },
      { k: 'nombre',    h: 'Nombre'    },
      { k: 'categoria', h: 'Categoría' },
      { k: 'estado',    h: 'Estado',   badge: true },
      { k: 'ubicacion', h: 'Ubicación' },
    ],
    fields: [
      { k: 'id',        l: 'ID',        req: true, createOnly: true },
      { k: 'nombre',    l: 'Nombre',    req: true },
      { k: 'categoria', l: 'Categoría', req: true },
      { k: 'estado',    l: 'Estado',    req: true },
      { k: 'ubicacion', l: 'Ubicación', req: true },
    ],
  },

  // ── Mantenimiento ──────────────────────────────────────────────
  // Modelo: Mantenimientoderecursos extends RecursoBase
  // RecursoBase: getId, getNombre, getCategoria, getEstado, getUbicacion
  // Propio: getFechadeingreso_mantenimientorecursos
  // NOTA: los campos del RecursoBase tienen columnas renombradas:
  //   nombre     → NOMBRE_TECNICO
  //   categoria  → DESCRIPCION
  //   ubicacion  → CATEGORIA (sí, confuso en el modelo original)
  // Setters POST/PATCH: setNombre, setCategoria, setEstado, setUbicacion,
  //   setFechadeingreso_mantenimientorecursos
  mantenimientos: {
    label: 'Mantenimiento',
    icon: 'bi-tools',
    path: 'mantenimientos',
    cols: [
      { k: 'id',        h: 'ID'          },
      { k: 'nombre',    h: 'Técnico'     },
      { k: 'categoria', h: 'Descripción' },
      { k: 'estado',    h: 'Estado',     badge: true },
      { k: 'ubicacion', h: 'Categoría'   },
      { k: 'fechadeingreso_mantenimientorecursos', h: 'Fecha' },
    ],
    fields: [
      { k: 'id',        l: 'ID',             req: true, createOnly: true },
      { k: 'nombre',    l: 'Nombre Técnico', req: true },
      { k: 'categoria', l: 'Descripción',    req: true },
      { k: 'estado',    l: 'Estado',         req: true },
      { k: 'ubicacion', l: 'Categoría',      req: true },
      { k: 'fechadeingreso_mantenimientorecursos', l: 'Fecha de Ingreso' },
    ],
  },

  // ── Usuarios (Verificacion) ────────────────────────────────────
  // Modelo: Verificacion extends Persona
  // @RepositoryRestResource(path="usuarios")
  // Getters: getId, getNombre, getCorreo,
  //   getContraseña_personaladiministrativo (campo ñ — funciona en JSON UTF-8)
  //   getTelefono_personaladministrativo
  // Setters POST/PATCH: setNombre, setCorreo,
  //   setContraseña_personaladiministrativo, setTelefono_personaladministrativo
  usuarios: {
    label: 'Usuarios',
    icon: 'bi-person-badge',
    path: 'usuarios',
    cols: [
      { k: 'id',                               h: 'ID'       },
      { k: 'nombre',                           h: 'Nombre'   },
      { k: 'correo',                           h: 'Correo'   },
      { k: 'telefono_personaladministrativo',  h: 'Teléfono' },
    ],
    fields: [
      { k: 'id',                                    l: 'ID',        req: true, createOnly: true },
      { k: 'nombre',                                l: 'Nombre',    req: true },
      { k: 'correo',                                l: 'Correo',    type: 'email' },
      { k: 'contraseña_personaladiministrativo',    l: 'Contraseña',type: 'password' },
      { k: 'telefono_personaladministrativo',       l: 'Teléfono'            },
    ],
  },
};

/* ═══════════════════════════════════════════════════════════════════
   HELPERS DE API
═══════════════════════════════════════════════════════════════════ */

async function apiFetch(url, opts = {}) {
  const res = await fetch(API + url, {
    headers: { 'Content-Type': 'application/json', ...opts.headers },
    ...opts,
  });
  if (!res.ok) {
    const text = await res.text().catch(() => '');
    throw new Error(`HTTP ${res.status}${text ? ': ' + text.slice(0, 120) : ''}`);
  }
  if (res.status === 204) return null;
  return res.json();
}

// Extrae el array de items del wrapper _embedded que devuelve Spring Data REST
function extractItems(data) {
  if (!data?._embedded) return [];
  const keys = Object.keys(data._embedded);
  return keys.length ? (data._embedded[keys[0]] ?? []) : [];
}

/* ═══════════════════════════════════════════════════════════════════
   ESTADO GLOBAL
═══════════════════════════════════════════════════════════════════ */
let currentEntity = null;
let editingId     = null;
let currentItems  = [];
let bsModal       = null;

/* ═══════════════════════════════════════════════════════════════════
   TABS LOGIN / REGISTRO
═══════════════════════════════════════════════════════════════════ */
function showTab(tab) {
  const isLogin = tab === 'login';

  const formLogin    = document.getElementById('formLogin');
  const formRegister = document.getElementById('formRegister');

  // Ocultar / mostrar formularios
  formLogin.style.display    = isLogin ? 'block' : 'none';
  formRegister.style.display = isLogin ? 'none'  : 'block';

  // Actualizar tabs activos
  document.getElementById('tabLogin').classList.toggle('active',    isLogin);
  document.getElementById('tabRegister').classList.toggle('active', !isLogin);

  // Limpiar alerta
  document.getElementById('loginErr').classList.add('d-none');

  // Animar el formulario visible (fromTo evita conflictos con estado anterior)
  const target = isLogin ? formLogin : formRegister;
  gsap.fromTo(target,
    { y: 16, opacity: 0 },
    { y: 0,  opacity: 1, duration: 0.35, ease: 'power2.out', overwrite: true }
  );
}

/* ═══════════════════════════════════════════════════════════════════
   LOGIN / LOGOUT
═══════════════════════════════════════════════════════════════════ */
async function login() {
  const id   = document.getElementById('lId').value.trim();
  const pass = document.getElementById('lPass').value;
  const err  = document.getElementById('loginErr');
  err.classList.add('d-none');

  if (!id || !pass) { showLoginErr('Completa tu ID y contraseña.'); return; }

  const btn = document.getElementById('btnLogin');
  btn.disabled = true;
  btn.innerHTML = '<span class="spinner-border spinner-border-sm me-2"></span>Verificando...';

  try {
    // GET /api/usuarios/{id}  →  exposeIdsFor garantiza que "id" esté en la respuesta
    const user = await apiFetch(`/usuarios/${encodeURIComponent(id)}`);

    // La contraseña se compara localmente (igual que hacía el App.java original)
    // campo: contraseña_personaladiministrativo  (getter getContraseña_...)
    const ok = user && user['contraseña_personaladiministrativo'] === pass;

    if (ok) {
      document.getElementById('hdrName').textContent = user.nombre || id;
      document.getElementById('loginPage').style.display  = 'none';
      document.getElementById('dashboard').style.display  = 'flex';
      initDashboard();
      loadEntity('proyectos');
    } else {
      showLoginErr('ID o contraseña incorrectos.');
    }
  } catch {
    showLoginErr('Usuario no encontrado o error de conexión con el servidor.');
  } finally {
    btn.disabled = false;
    btn.innerHTML = '<i class="bi bi-box-arrow-in-right me-2"></i>Iniciar sesión';
  }
}

function showLoginErr(msg) {
  showAuthMsg(msg, 'danger');
}

function logout() {
  document.getElementById('dashboard').style.display = 'none';
  document.getElementById('loginPage').style.display = 'flex';
  document.getElementById('lId').value   = '';
  document.getElementById('lPass').value = '';
  gsap.from('#loginCard', { y: 30, opacity: 0, duration: 0.5, ease: 'power3.out' });
}

/* ═══════════════════════════════════════════════════════════════════
   REGISTRO — POST /api/usuarios
   Campos: id, nombre, correo, contraseña_personaladiministrativo,
           telefono_personaladministrativo
   (coincide exactamente con los setters de Verificacion.java)
═══════════════════════════════════════════════════════════════════ */
async function register() {
  const id       = document.getElementById('rId').value.trim();
  const nombre   = document.getElementById('rNombre').value.trim();
  const correo   = document.getElementById('rCorreo').value.trim();
  const telefono = document.getElementById('rTelefono').value.trim();
  const pass     = document.getElementById('rPass').value;

  // Validación de campos obligatorios
  if (!id)     { showAuthMsg('El ID es obligatorio.',          'danger'); document.getElementById('rId').focus();     return; }
  if (!nombre) { showAuthMsg('El nombre es obligatorio.',      'danger'); document.getElementById('rNombre').focus(); return; }
  if (!pass)   { showAuthMsg('La contraseña es obligatoria.',  'danger'); document.getElementById('rPass').focus();   return; }

  const btn = document.getElementById('btnRegister');
  btn.disabled = true;
  btn.innerHTML = '<span class="spinner-border spinner-border-sm me-2"></span>Creando cuenta...';

  try {
    // Paso 1: verificar que el ID no esté ya registrado
    let idOcupado = false;
    try {
      await apiFetch(`/usuarios/${encodeURIComponent(id)}`);
      idOcupado = true; // si no lanzó excepción, el usuario existe
    } catch (checkErr) {
      // 404 = ID disponible, cualquier otro error es inesperado
      if (!checkErr.message.startsWith('HTTP 404')) {
        throw new Error('No se pudo verificar el ID: ' + checkErr.message);
      }
    }

    if (idOcupado) {
      showAuthMsg(`El ID "${esc(id)}" ya está registrado. Elige otro.`, 'danger');
      return;
    }

    // Paso 2: crear el usuario — POST /api/usuarios
    await apiFetch('/usuarios', {
      method: 'POST',
      body: JSON.stringify({
        id:     id,
        nombre: nombre,
        correo: correo,
        'contraseña_personaladiministrativo': pass,
        telefono_personaladministrativo:      telefono,
      }),
    });

    showAuthMsg('¡Cuenta creada exitosamente! Ya puedes iniciar sesión.', 'success');
    ['rId', 'rNombre', 'rCorreo', 'rTelefono', 'rPass'].forEach(fieldId => {
      document.getElementById(fieldId).value = '';
    });
    setTimeout(() => showTab('login'), 1800);

  } catch (e) {
    showAuthMsg('Error al crear la cuenta: ' + e.message, 'danger');
  } finally {
    btn.disabled = false;
    btn.innerHTML = '<i class="bi bi-person-check me-2"></i>Crear cuenta';
  }
}

function showAuthMsg(msg, type) {
  const el = document.getElementById('loginErr');
  el.className = `alert alert-${type} py-2 small`;
  el.textContent = msg;
  el.classList.remove('d-none');
  gsap.from(el, { y: -8, opacity: 0, duration: 0.3, ease: 'power2.out' });
}

// Enter en login
document.addEventListener('keydown', e => {
  if (e.key === 'Enter' && document.getElementById('loginPage').style.display !== 'none') {
    const loginVisible = document.getElementById('formLogin').style.display !== 'none';
    if (loginVisible) login(); else register();
  }
});

/* ═══════════════════════════════════════════════════════════════════
   INICIALIZACIÓN DEL DASHBOARD (GSAP)
═══════════════════════════════════════════════════════════════════ */
function initDashboard() {
  gsap.registerPlugin(ScrollTrigger);

  // Navbar desliza desde arriba
  gsap.from('.app-navbar', {
    y: -40, opacity: 0, duration: 0.5, ease: 'power3.out',
  });

  buildSidebar();

  // Items del sidebar aparecen en cascada
  gsap.from('.nav-item-link', {
    x: -30, opacity: 0, duration: 0.4,
    stagger: 0.07, delay: 0.25,
    ease: 'power2.out',
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
   CARGAR ENTIDAD — llama a GET /api/{path}?size=200
═══════════════════════════════════════════════════════════════════ */
async function loadEntity(key) {
  currentEntity = key;
  const cfg = ENTITIES[key];

  // Marcar activo en sidebar
  document.querySelectorAll('.nav-item-link').forEach(n => n.classList.remove('active'));
  document.getElementById(`nav-${key}`)?.classList.add('active');

  // Matar ScrollTriggers previos para no acumularlos
  ScrollTrigger.getAll().forEach(t => t.kill());

  // Mostrar cabecera + spinner
  document.getElementById('mainContent').innerHTML = `
    <div class="d-flex justify-content-between align-items-start mb-3" id="pageHead">
      <div>
        <div class="page-title">${cfg.label}</div>
        <div class="page-count" id="pageCount"></div>
      </div>
      <button class="btn btn-primary btn-sm fw-semibold" onclick="openCreate()">
        <i class="bi bi-plus-lg me-1"></i>Nuevo
      </button>
    </div>
    <div class="card content-card" id="dataCard">
      <div class="state-box">
        <div class="d-flex justify-content-center mb-3">
          <div class="spin-ring"></div>
        </div>
        Cargando datos...
      </div>
    </div>`;

  // Animar cabecera de página
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

  // Animación de entrada del card
  gsap.from('#dataCard', { y: 24, opacity: 0, duration: 0.45, ease: 'power3.out' });

  // Filas en cascada usando ScrollTrigger con scroller personalizado
  gsap.fromTo('.tbl-row',
    { opacity: 0, y: 14 },
    {
      opacity: 1, y: 0,
      duration: 0.3,
      stagger: 0.04,
      delay: 0.1,
      ease: 'power2.out',
      onComplete: () => {
        // ScrollTrigger para filas que entren al viewport al hacer scroll
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

/* ── Formatea el valor de una celda ────────────────────────────── */
function fmtCell(val, col) {
  if (val == null || val === '') return '<span class="text-muted small">—</span>';
  const s = String(val);
  if (!col.badge) return esc(s);
  const v = s.toLowerCase();
  const cls = v.includes('activ')   ? 'badge-green'
            : v.includes('inactiv') ? 'badge-red'
            : v.includes('pendient')? 'badge-blue'
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

  // Campos aparecen en cascada
  gsap.fromTo('.form-field-item',
    { opacity: 0, y: 10 },
    { opacity: 1, y: 0, duration: 0.25, stagger: 0.06, delay: 0.05, ease: 'power2.out' }
  );
}

/* ── Guardar (POST para nuevo, PATCH para editar) ──────────────── */
async function saveRecord() {
  const cfg    = ENTITIES[currentEntity];
  const isEdit = editingId !== null;
  const body   = {};

  // Recolectar valores del formulario
  for (const f of cfg.fields) {
    if (isEdit && f.createOnly) continue;
    const el = document.getElementById(`f-${f.k}`);
    if (!el) continue;
    const v = el.value.trim();
    if (f.req && !v) {
      el.classList.add('is-invalid');
      toast(`El campo "${f.l}" es obligatorio.`, 'danger');
      el.focus();
      return;
    }
    el.classList.remove('is-invalid');
    // Incluir aunque esté vacío para que PATCH pueda limpiar el campo
    body[f.k] = v;
  }

  const btn = document.getElementById('btnSave');
  btn.disabled = true;
  btn.innerHTML = '<span class="spinner-border spinner-border-sm me-1"></span>Guardando...';

  try {
    if (isEdit) {
      // PATCH → actualización parcial, preserva campos que no enviamos
      await apiFetch(`/${cfg.path}/${encodeURIComponent(editingId)}`, {
        method: 'PATCH',
        body: JSON.stringify(body),
      });
      toast('Registro actualizado correctamente.', 'success');
    } else {
      // POST → crear nuevo recurso
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

/* ── Eliminar ──────────────────────────────────────────────────── */
async function askDelete(id) {
  if (!confirm(`¿Eliminar el registro "${id}"?\nEsta acción no se puede deshacer.`)) return;
  const cfg = ENTITIES[currentEntity];
  try {
    await apiFetch(`/${cfg.path}/${encodeURIComponent(id)}`, { method: 'DELETE' });
    toast('Registro eliminado.', 'success');
    loadEntity(currentEntity);
  } catch (e) {
    toast(`Error al eliminar: ${e.message}`, 'danger');
  }
}

/* ═══════════════════════════════════════════════════════════════════
   TOASTS (Bootstrap + GSAP)
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

  // Animación de entrada
  gsap.fromTo(el,
    { x: 60, opacity: 0 },
    { x: 0,  opacity: 1, duration: 0.35, ease: 'power3.out' }
  );

  // Fade out antes de que Bootstrap lo quite
  setTimeout(() => {
    gsap.to(el, { x: 60, opacity: 0, duration: 0.3, ease: 'power2.in',
      onComplete: () => el.remove() });
  }, 3200);
}

/* ═══════════════════════════════════════════════════════════════════
   UTILIDADES
═══════════════════════════════════════════════════════════════════ */
function esc(s) {
  return String(s)
    .replace(/&/g, '&amp;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;')
    .replace(/"/g, '&quot;');
}

/* ═══════════════════════════════════════════════════════════════════
   ANIMACIÓN INICIAL — Login card
═══════════════════════════════════════════════════════════════════ */
gsap.registerPlugin(ScrollTrigger);
gsap.from('#loginCard', { y: 50, opacity: 0, duration: 0.7, ease: 'power3.out' });
