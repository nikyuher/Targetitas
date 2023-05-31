$(document).ready(function() {
    $.ajax({
        type: 'GET',
        url: 'Producto',
        dataType: 'html',
        success: function(data) {
            $('#productos').html(data);
        },
        error: function() {
            alert('Error al llamar al Servlet');
        }
    });
});

//Aparicion del boton añadir
var caja = document.querySelector('.caja');

caja.addEventListener('mouseover', function() {
  var boton = this.querySelector('button');
  boton.style.display = 'block';
});

caja.addEventListener('mouseout', function() {
  var boton = this.querySelector('button');
  boton.style.display = 'none';
});