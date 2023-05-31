$(document).ready(function() {
  // Verificar si hay valores en sessionStorage
  if (sessionStorage.getItem('nombreUsuario')) {
    // Obtener el nombre de usuario y el ID de usuario
    var nombreUsuario = sessionStorage.getItem('nombreUsuario');
    
    // Modificar el texto del enlace "Inicia Sesión" con el nombre de usuario y el ID
    $('#nombreUsuario').html(nombreUsuario);
  }
});