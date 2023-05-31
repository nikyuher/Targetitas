//Actualizar datos del formulario
document.addEventListener("DOMContentLoaded", function() {
  function actualizarDatos(event) {
    event.preventDefault();
    
    //IDs ingresar datos
    document.getElementById("nombre1").value = "";
    document.getElementById("correo1").value = "";
    document.getElementById("contra1").value = "";
    document.getElementById("precio1").value = "";
    
    //IDs eliminar datos
    document.getElementById("idusuario2").value = "";
    document.getElementById("nombre2").value = "";
    document.getElementById("correo2").value = "";
    
    //IDs actualizar datos
    document.getElementById("idusuario3").value = "";
    document.getElementById("nombre3").value = "";
    document.getElementById("correo3").value = "";
    document.getElementById("contra3").value = "";
    document.getElementById("precio3").value = "";
    
    //IDs mostrar datos
    document.getElementById("nombre4").value = "";
    document.getElementById("correo4").value = "";
    
  }
  
  document.getElementById("cont-admin").addEventListener("submit", actualizarDatos);
});

//Ingresar Datos
document.addEventListener("DOMContentLoaded", function() {
  // Obtener los elementos del formulario
  var select = document.getElementById("tablas1");
  var descripcionBox = document.getElementById("descripcionBox1");
  var contraBox = document.getElementById("contraBox1");
  var nombreBox = document.getElementById("nombreBox1");

  // Función para actualizar los campos del formulario 
  function actualizarCampos() {
    var selectedValue = select.value;

    if (selectedValue === "productos") {
      nombreBox.innerHTML = `
        <input type="text" name="" id="nombre1" required>
        <label>Producto</label>
      `;
      descripcionBox.innerHTML = `
        <input type="text" name="" min="25" max="63" id="correo1" required>
        <label>Descripción</label>
      `;

      contraBox.innerHTML = `
        <div>
          <input type="text" name="" id="contra1" required>
          <label>Imagen</label>
        </div>
        <div>
          <input type="number" name="" id="precio1" required>
          <label>Precio</label>
        </div>
      `;
    } else {
      nombreBox.innerHTML = `
        <input type="text" name="" id="nombre1" required>
        <label>Nombre</label>
      `;

      descripcionBox.innerHTML = `
        <input type="text" name="" id="correo1" required>
        <label>Email</label>
      `;

      contraBox.innerHTML = `
        <input type="text" name="" id="contra1" required>
        <label>Contraseña</label>
      `;
    }
  }

  select.addEventListener("change", actualizarCampos);
  actualizarCampos();
});

//Eliminar Datos
document.addEventListener("DOMContentLoaded", function() {
  // Obtener los elementos del formulario
  var select = document.getElementById("tablas2");
  var descripcionBox = document.getElementById("descripcionBox2");
  var nombreBox = document.getElementById("nombreBox2");

  // Función para actualizar los campos del formulario 
  function actualizarCampos() {
    var selectedValue = select.value;

    if (selectedValue === "productos") {
      nombreBox.innerHTML = `
        <input type="text" name="" id="nombre2" required>
        <label>Producto</label>
      `;
      descripcionBox.innerHTML = `
      `;
    } else {
      nombreBox.innerHTML = `
        <input type="text" name="" id="nombre2" required>
        <label>Nombre</label>
      `;

      descripcionBox.innerHTML = `
        <input type="text" name="" id="correo2" required>
        <label>Email</label>
      `;
    }
  }

  select.addEventListener("change", actualizarCampos);
  actualizarCampos();
});

//Actualizar Datos
document.addEventListener("DOMContentLoaded", function() {
  // Obtener los elementos del formulario
  var select = document.getElementById("tablas3");
  var descripcionBox = document.getElementById("descripcionBox3");
  var contraBox = document.getElementById("contraBox3");
  var nombreBox = document.getElementById("nombreBox3");

  // Función para actualizar los campos del formulario 
  function actualizarCampos() {
    var selectedValue = select.value;

    if (selectedValue === "productos") {
      nombreBox.innerHTML = `
        <input type="text" name="" id="nombre3" required>
        <label>Producto</label>
      `;
      descripcionBox.innerHTML = `
        <input type="text" name="" min="25" max="63" id="correo3" required>
        <label>Descripción</label>
      `;

      contraBox.innerHTML = `
        <div>
          <input type="text" name="" id="contra3" required>
          <label>Imagen</label>
        </div>
        <div>
          <input type="number" name="" id="precio3" required>
          <label>Precio</label>
        </div>
      `;
    } else {
      nombreBox.innerHTML = `
        <input type="text" name="" id="nombre3" required>
        <label>Nombre</label>
      `;

      descripcionBox.innerHTML = `
        <input type="text" name="" id="correo3" required>
        <label>Email</label>
      `;

      contraBox.innerHTML = `
        <input type="text" name="" id="contra3" required>
        <label>Contraseña</label>
      `;
    }
  }

  select.addEventListener("change", actualizarCampos);
  actualizarCampos();
});
//Mostrar Datos
document.addEventListener("DOMContentLoaded", function() {
  // Obtener los elementos del formulario
  var select = document.getElementById("tablas4");
  var descripcionBox = document.getElementById("descripcionBox4");
  var nombreBox = document.getElementById("nombreBox4");

  // Función para actualizar los campos del formulario 
  function actualizarCampos() {
    var selectedValue = select.value;

    if (selectedValue === "productos") {
      nombreBox.innerHTML = `
        <input type="text" name="" id="nombre4" required>
        <label>Producto</label>
      `;
      descripcionBox.innerHTML = `
      `;
    } else {
      nombreBox.innerHTML = `
        <input type="text" name="" id="nombre4" required>
        <label>Nombre</label>
      `;

      descripcionBox.innerHTML = `
        <input type="text" name="" id="correo4" required>
        <label>Email</label>
      `;
    }
  }

  select.addEventListener("change", actualizarCampos);
  actualizarCampos();
});