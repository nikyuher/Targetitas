$(document).ready(function() {
  $('#formulario').submit(function(event) {
    
    event.preventDefault();
    
    
    var nombre = $('#nombre').val();
    var correo = $('#correo').val();
    var contrasena = $('#contra').val();
    
    // Enviar los datos del formulario a la servlet con AJAX
    $.ajax({
      type: 'POST',
      url: 'registrarse',
      data: { nombre: nombre, correo: correo, contra: contrasena},
      success: function(data) {
        
        $('#respuesta').html(data);
        
      },
      error: function() {
        
        alert('Error al registrarse');
      }
    });
  });
});

//Login
$(document).ready(function() {
  $('#formularioL').submit(function(event) {
    
    event.preventDefault();

    var correo = $('#correoL').val();
    var contrasena = $('#contrasenaL').val();
    
    // Enviar los datos del formulario a la servlet con AJAX
    $.ajax({
      type: 'GET',
      url: 'registrarse',
      data: { correoL: correo, contrasenaL: contrasena},
      success: function(data) {
          
          $('#respuestaL').html(data);
          if (data.indexOf("admin") !== -1) {
              
              var nombreUsuario = data; 

              sessionStorage.setItem('nombreUsuario', nombreUsuario);
              
              window.location.href = 'admin.html';
        }else{
            var nombreUsuario = data; 

              sessionStorage.setItem('nombreUsuario', nombreUsuario);
              
              window.location.href = 'index.html';
        }
      }, 
      error: function() {
      
        alert('Error al Inicias Sesion');
      }
    });
  });
});
