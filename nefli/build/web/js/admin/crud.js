//Ingresar datos a Administrador
$(document).ready(function() {
  $('#ingresar').submit(function(event) {
    
    event.preventDefault();
    
    var tabla = $('#tablas1').val();
    var precio = $('#precio1').val();
    var nombre = $('#nombre1').val();
    var correo = $('#correo1').val();
    var contrasena = $('#contra1').val();
    
    // Enviar los datos del formulario a la servlet con AJAX
    $.ajax({
      type: 'POST',
      url: 'PostGet',
      data: {tablas1:tabla,precio1:precio, nombre1: nombre, correo1: correo, contra1: contrasena},
      success: function(data) {
        
        $('#respuesta1').html(data);
        
      },
      error: function() {
        
        alert('Error al Ingresar');
      }
    });
  });
});

//Eliminar datos en Administrador
$(document).ready(function() {
  $('#eliminar').submit(function(event) {
    
    event.preventDefault();
    
    var tabla = $('#tablas2').val();
    var idusuario = $('#idusuario2').val();
    var nombre = $('#nombre2').val();
    var correo = $('#correo2').val();
    
    // Enviar los datos del formulario a la servlet con AJAX
    $.ajax({
      type: 'POST',
      url: 'Eliminar',
      data: {tablas2:tabla, idusuario2:idusuario, nombre2: nombre, correo2: correo},
      success: function(data) {
        
        $('#respuesta2').html(data);
        
      },
      error: function() {
        
        alert('Error al eliminar');
      }
    });
  });
});
//Actualizar datos
$(document).ready(function() {
  $('#actualizar').submit(function(event) {
    
    event.preventDefault();
    
    var tabla = $('#tablas3').val();
    var id = $('#idusuario3').val();
    var nombre = $('#nombre3').val();
    var correo = $('#correo3').val();
    var contra = $('#contra3').val();
    var precio = $('#precio3').val();
    
    // Enviar los datos del formulario a la servlet con AJAX
    $.ajax({
      type: 'POST',
      url: 'Actualizar',
      data: {tablas3:tabla, idusuario3: id, nombre3: nombre, correo3: correo, contra3: contra, precio3: precio},
      success: function(data) {
        
        $('#respuesta3').html(data);
        
      },
      error: function() {
        
        alert('Error al Llamar');
      }
    });
  });
});
//MostrarDatos en Administrador
$(document).ready(function() {
  $('#llamar').submit(function(event) {
    
    event.preventDefault();
    
    var tabla = $('#tablas4').val();
    var nombre = $('#nombre4').val();
    var correo = $('#correo4').val();
    
    // Enviar los datos del formulario a la servlet con AJAX
    $.ajax({
      type: 'GET',
      url: 'PostGet',
      data: {tablas4:tabla, nombre4: nombre, correo4: correo},
      success: function(data) {
        
        $('#respuesta4').html(data);
        
      },
      error: function() {
        
        alert('Error al Llamar');
      }
    });
  });
});