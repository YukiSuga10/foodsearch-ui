document.getElementById('addBtn').addEventListener('click', () => {
  const newForm = document.createElement('input');
  newForm.type = 'text';

  const newLabel = document.createElement('label');
  newLabel.textContent = '材料：';

  newLabel.appendChild(newForm);
  document.querySelector('div').appendChild(newLabel);
});