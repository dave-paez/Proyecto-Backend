/* ═══════════════════════════════════════════════════════════════════
   CONFIGURACIÓN DE ENTIDADES  (compartido entre login y dashboard)
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
  patrocinios: {
    label: 'Patrocinios',
    icon: 'bi-trophy',
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
  // NOTA: los campos del RecursoBase tienen columnas renombradas:
  //   nombre → NOMBRE_TECNICO, categoria → DESCRIPCION, ubicacion → CATEGORIA
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
  // @RepositoryRestResource(path="usuarios")
  // NOTA: el campo contraseña tiene un typo en el modelo Java
  //   "adiministrativo" (debería ser "administrativo") — no corregir
  //   aquí sin también migrar la columna en BD.
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
   UTILIDADES
═══════════════════════════════════════════════════════════════════ */
function esc(s) {
  return String(s)
    .replace(/&/g, '&amp;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;')
    .replace(/"/g, '&quot;');
}
