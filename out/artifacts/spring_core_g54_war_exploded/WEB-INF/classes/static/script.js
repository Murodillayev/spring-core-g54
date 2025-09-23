// Kategoriya tahrirlash
function editCategory(id, name, description) {
    document.getElementById('editCategoryName').value = name;
    document.getElementById('editCategoryDescription').value = description || '';
    document.getElementById('editCategoryForm').action = '/category/' + id;

    const modal = new bootstrap.Modal(document.getElementById('editCategoryModal'));
    modal.show();
}

// Kategoriya o'chirish
function deleteCategory(id, name) {
    document.getElementById('deleteCategoryName').textContent = name;
    document.getElementById('deleteCategoryForm').action = '/category/' + id;

    const modal = new bootstrap.Modal(document.getElementById('deleteCategoryModal'));
    modal.show();
}

// Success va error xabarlari uchun
document.addEventListener('DOMContentLoaded', function () {
    // URL parametrlaridan success yoki error xabarlarini ko'rsatish
    const urlParams = new URLSearchParams(window.location.search);
    const success = urlParams.get('success');
    const error = urlParams.get('error');

    if (success) {
        showAlert('success', success);
    }
    if (error) {
        showAlert('danger', error);
    }
});

function showAlert(type, message) {
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type} alert-dismissible fade show`;
    alertDiv.innerHTML = `
                ${message}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            `;


    const container = document.querySelector('.container');
    container.insertBefore(alertDiv, container.firstChild);

    // 5 soniyadan keyin avtomatik yopish
    setTimeout(() => {
        alertDiv.remove();
    }, 5000);
}
