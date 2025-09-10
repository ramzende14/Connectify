console.log("Hello");

const viewContactModal = document.getElementById('view_contact_modal');

const options = {
  placement: 'bottom-right',
  backdrop: 'dynamic',
  backdropClasses: 'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
  closable: true,
  onHide: () => { console.log('modal is hidden'); },
  onShow: () => { console.log('modal is shown'); },
  onToggle: () => { console.log('modal has been toggled'); },
};

const instanceOptions = {
  id: 'view_contact_modal',
  override: true
};

const contactModal = new Modal(viewContactModal, options, instanceOptions);

function openContactModal() {
  contactModal.show();
}
