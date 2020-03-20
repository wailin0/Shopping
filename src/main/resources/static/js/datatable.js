$(document).ready(function() {
    var filesTable = $("#shopTable").DataTable({
        "ajax": { 
            "url": "/api/products",
            "dataSrc": "_embedded.products"
        },
        "columns": [
            { "data": "name" },
            { "data": "price" },
            { "data": "category" },
            { "data": "info" }
        ]
    });
});