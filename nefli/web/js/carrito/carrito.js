//Mostrar los productos añadidos por los usuarios 
$(document).ready(function() {
    
    var id = $('#obtID').text();
    
    
    $.ajax({
        type: 'GET',
        url: 'carrito',
        data: { obtID:id},
        success: function(data) {
            $('#contCarrito').html(data);
        },
        error: function() {
            alert('Error al Mostrar Datos');
        }
    });
});
//Eliminar carrito con boton
$(document).ready(function() {
  $(document).on('click', '.botonCompra', function(event) {
    event.preventDefault();
    
    var idUsuario = $('#obtID').text();
    
    
    // Enviar los datos del formulario a la servlet con AJAX
    $.ajax({
      type: 'POST',
      url: 'deleteCarrito',
      data: { obtID: idUsuario},
      success: function(data) {
        $('#contCarrito').html(data);
      }, 
      error: function() {
        alert('Error al Mostrar Datos');
      }
    });
  });
});

//Añadir productos al Usuario
$(document).ready(function() {
  $(document).on('click', '.caja .añadirP', function(event) {
    event.preventDefault();
    
    var idUsuario = $('#obtID').text();
    
    var caja = $(this).closest('.caja');
    var idProduct = caja.find('.idProducto').text();
    var precio = caja.find('.precioP').text();
    
    
    // Enviar los datos del formulario a la servlet con AJAX
    $.ajax({
      type: 'POST',
      url: 'carrito',
      data: { obtID: idUsuario, idProducto: idProduct, precioP: precio },
      success: function(data) {
        console.log(data);
      }, 
      error: function() {
        alert('Error al Mostrar Datos');
      }
    });
  });
});
