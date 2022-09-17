function enviar() {
    $.ajax({
        data:  $("#formulario").serialize(),
        type: 'POST',
        url: '/api/guardar'
        
    });
    return false
}