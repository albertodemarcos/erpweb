function destruirLineaArticulo(id) {
    console.log('Enmtramos a destruir2');
    if (id != null && id !== 'undefined') {
        var lineaArticuloId = 'linea_art_id_' + id;
        jQuery('#' + lineaArticuloId).remove();
    } else {
        alert('Error', 'Error, no se puede eliiminar la fila, inténtalo mas tarde', 'error');
    }
}