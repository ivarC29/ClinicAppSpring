const modal_label = document.getElementById("appointment-modal-label");
const appointentDate = document.querySelector('.appointment-date');

document.addEventListener('DOMContentLoaded', function() {
	const calendarElement = document.getElementById('calendar');
	const myModal = new bootstrap.Modal(document.getElementById("modalAppointment"), {});
	let data = [];
	
	renderCalendar(data, calendarElement, myModal);
	
});

const fillModal = ( title, date ) => {
	modal_label.innerHTML = title;
    appointentDate.value = date;
}

const renderCalendar = (data, element, modal) => {
	const calendar = new FullCalendar.Calendar(element, {
    		timeZone: 'UTC',
    		initialView: 'dayGridMonth',
    		themeSystem: 'bootstrap5',
    		headerToolbar: {
    		      left: 'prev,next today',
    		      center: 'title',
    		      right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
    		},
    		events: data,
    		editable: true,
    		dateClick: function(info) {
    		    modal.show();
    		    fillModal( info.view.title, info.dateStr);
    		},
    		eventClick: function(info) {
    			let appointmentDate = info.event.start;

    			var appointmentDateUtc = appointmentDate.toUTCString().split(' ')[4];

    			info.jsEvent.preventDefault();
    			swal(info.event.title, "Fecha: " + appointmentDateUtc + " fin: " + horaFinUTC);
    		},
    		locales: [
    			'es',
    			'en'
    		],
    		eventTimeFormat: { // like '14:30:00'
    		hour: '2-digit',
    		minute: '2-digit',
    		second: '2-digit',
    		meridiem: false
    		}
    });
	calendar.render();
}