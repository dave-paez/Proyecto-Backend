/* ═══════════════════════════════════════════════════════════════════
   LOGIN PAGE — lógica exclusiva de index.html
   Depende de: app.js (apiFetch, esc)
═══════════════════════════════════════════════════════════════════ */

/* ── Tabs: Iniciar sesión / Registrarse ─────────────────────────── */
function showTab(tab) {
  const isLogin = tab === 'login';

  const formLogin    = document.getElementById('formLogin');
  const formRegister = document.getElementById('formRegister');

  formLogin.style.display    = isLogin ? 'block' : 'none';
  formRegister.style.display = isLogin ? 'none'  : 'block';

  document.getElementById('tabLogin').classList.toggle('active',    isLogin);
  document.getElementById('tabRegister').classList.toggle('active', !isLogin);

  document.getElementById('loginErr').classList.add('d-none');

  const target = isLogin ? formLogin : formRegister;
  gsap.fromTo(target,
    { y: 16, opacity: 0 },
    { y: 0,  opacity: 1, duration: 0.35, ease: 'power2.out', overwrite: true }
  );
}

/* ── Login ──────────────────────────────────────────────────────── */
async function login() {
  const id   = document.getElementById('lId').value.trim();
  const pass = document.getElementById('lPass').value;

  document.getElementById('loginErr').classList.add('d-none');

  if (!id || !pass) { showAuthMsg('Completa tu ID y contraseña.', 'danger'); return; }

  const btn = document.getElementById('btnLogin');
  btn.disabled = true;
  btn.innerHTML = '<span class="spinner-border spinner-border-sm me-2"></span>Verificando...';

  try {
    const user = await apiFetch(`/usuarios/${encodeURIComponent(id)}`);

    const ok = user && user['contraseña_personaladiministrativo'] === pass;

    if (ok) {
      // Guardar sesión y redirigir al dashboard
      sessionStorage.setItem('sessionUser', JSON.stringify({
        id:     user.id     || id,
        nombre: user.nombre || id,
      }));
      window.location.href = '/dashboard.html';
    } else {
      showAuthMsg('ID o contraseña incorrectos.', 'danger');
    }
  } catch {
    showAuthMsg('Usuario no encontrado o error de conexión con el servidor.', 'danger');
  } finally {
    btn.disabled = false;
    btn.innerHTML = '<i class="bi bi-box-arrow-in-right me-2"></i>Iniciar sesión';
  }
}

/* ── Registro ───────────────────────────────────────────────────── */
async function register() {
  const id       = document.getElementById('rId').value.trim();
  const nombre   = document.getElementById('rNombre').value.trim();
  const correo   = document.getElementById('rCorreo').value.trim();
  const telefono = document.getElementById('rTelefono').value.trim();
  const pass     = document.getElementById('rPass').value;

  if (!id)     { showAuthMsg('El ID es obligatorio.',         'danger'); document.getElementById('rId').focus();     return; }
  if (!nombre) { showAuthMsg('El nombre es obligatorio.',     'danger'); document.getElementById('rNombre').focus(); return; }
  if (!pass)   { showAuthMsg('La contraseña es obligatoria.', 'danger'); document.getElementById('rPass').focus();   return; }

  const btn = document.getElementById('btnRegister');
  btn.disabled = true;
  btn.innerHTML = '<span class="spinner-border spinner-border-sm me-2"></span>Creando cuenta...';

  try {
    // Verificar que el ID no esté ya registrado
    let idOcupado = false;
    try {
      await apiFetch(`/usuarios/${encodeURIComponent(id)}`);
      idOcupado = true;
    } catch (checkErr) {
      if (!checkErr.message.startsWith('HTTP 404')) {
        throw new Error('No se pudo verificar el ID: ' + checkErr.message);
      }
    }

    if (idOcupado) {
      showAuthMsg(`El ID "${esc(id)}" ya está registrado. Elige otro.`, 'danger');
      return;
    }

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

/* ── Helpers de alerta ──────────────────────────────────────────── */
function showAuthMsg(msg, type) {
  const el = document.getElementById('loginErr');
  el.className = `alert alert-${type} py-2 small`;
  el.textContent = msg;
  el.classList.remove('d-none');
  gsap.from(el, { y: -8, opacity: 0, duration: 0.3, ease: 'power2.out' });
}

/* ── Enter para enviar formulario ───────────────────────────────── */
document.addEventListener('keydown', e => {
  if (e.key !== 'Enter') return;
  const loginVisible = document.getElementById('formLogin').style.display !== 'none';
  if (loginVisible) login(); else register();
});

/* ── Animación inicial ──────────────────────────────────────────── */
gsap.registerPlugin(ScrollTrigger);
gsap.from('#loginCard', { y: 50, opacity: 0, duration: 0.7, ease: 'power3.out' });
