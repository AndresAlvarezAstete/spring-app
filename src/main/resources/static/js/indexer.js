// ubicaciones en vista

const paths = {
  '/salones': ['DashboardSalones'],
  '/salones/clientes/listar': ['navItemMenuClientes', 'collapseItemListarClientes'],
  '/salones/clientes/detalle': ['navItemMenuClientes', 'collapseItemListarClientes'],
  '/salones/beneficios/listar': ['navItemMenuBeneficios', 'collapseItemListarBeneficios'],
  '/salones/beneficios/detalle': ['navItemMenuBeneficios', 'collapseItemListarBeneficios'],
  '/salones/beneficios/modifica/listar': ['navItemMenuBeneficios', 'collapseItemListarModificaciones'],
  '/salones/productos/listar': ['navItemMenuProductos', 'collapseItemListarProductos'],
  '/salones/cargas/segmentos/listar': ['navItemMenuProductos', 'collapseItemListarCargaSegmento'],
  '/salones/productos/detalle': ['navItemMenuProductos', 'collapseItemListarProductos'],
  '/salones/cruces/listar': ['navItemMenuCruces', 'collapseItemListarCruces'],
  '/salones/valores/listar': ['navItemMenuCruces', 'collapseItemValores'],
  '/salones/valores/detalle': ['navItemMenuCruces', 'collapseItemValores'],
  '/salones/movimientos/listar': ['navItemMenuOperaciones', 'collapseItemListarMovimientos'],
  '/salones/movimientos/detalle': ['navItemMenuOperaciones', 'collapseItemListarMovimientos'],
  '/salones/accesos/detalle': ['navItemMenuOperaciones', 'collapseItemListarAccesos'],
  '/salones/accesos/listar': ['navItemMenuOperaciones', 'collapseItemListarAccesos'],
  '/salones/agencias/listar': ['navItemMenuAgencias', 'collapseItemListarAgencias'],
  '/salones/agencias/detalle': ['navItemMenuAgencia', 'collapseItemListarAgencias'],
  '/salones/salones/listar': ['navItemMenuSalones', 'collapseItemListarSalones'],
  '/salones/salones/detalle': ['navItemMenuSalones', 'collapseItemListarSalones'],
  '/salones/clientes/bch/listar': ['navItemMenuClientes', 'collapseItemListarClientesBch'],
  '/salones/clientes/bch/detalle': ['navItemMenuClientes', 'collapseItemListarClientesBch'],
  '/salones/ventas/detalle': ['navItemMenuOperaciones', 'collapseItemListarMovimientos'],
  '/salones/ventas/listar': ['navItemMenuOperaciones', 'collapseItemListarMovimientos'],
  '/salones/carros/detalle': ['navItemMenuOperaciones', 'collapseItemListarCarros'],
  '/salones/carros/listar': ['navItemMenuOperaciones', 'collapseItemListarCarros'],
  '/salones/qrs/listar': ['navItemMenuQrs', 'collapseItemListarQrs'],
  '/salones/qrs/modificaciones/listar': ['navItemMenuQrs', 'collapseItemListarQrsModificaciones'],
  '/salones/qrs/detalle' : ['navItemMenuQrs', 'collapseItemListarQrs'],
}
/*
navItemMenuBeneficios
navItemMenuBeneficiosCargas
navItemMenuBeneficiosCarros
navItemMenuBeneficiosAdmin
*/

let arr = paths[location.pathname];
if (arr !== undefined) {
  for (const item1 of arr) {
    document.getElementById(item1).classList.add('active');
    if (item1.startsWith('navItem')) {
      document.getElementById(item1).querySelectorAll('.collapse').forEach(item => {
        $(`#${item.id}`).toggle()
      });
    }
  }
}
