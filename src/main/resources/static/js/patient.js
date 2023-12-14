const listGroupPatient = document.querySelector('.list-group');
let patient;
let appointments;


const getPatientAppointments = async() => {
	// const res = await fetch(`/doctor/get/${id}`);
    const res = await fetch('/patient/getAppointments');
	const data = await res.json();

	return data;
}

const renderListAppointments = async() => {
    if (!patient)
        appointments = await getPatientAppointments();

    listGroupPatient.innerHTML = '';
    appointments.forEach( (appointment) => {
        const li = `
        <a href="#" class="list-group-item list-group-item-action list-group-item-primary">
            <div class="d-flex w-100 justify-content-between">
            <h5 class="mb-1">${appointment.medicalSpeciality}</h5>
            <small>${appointment.appointmentDate}</small>
            </div>
            <p class="mb-1">${appointment.doctorName}</p>
            <small>${(appointment.diagnosis)? appointment.diagnosis: 'Aun no tiene diagnostico'}</small>
        </a>
        `
        listGroupPatient.innerHTML += li;
    });
}

// Listeners
document.addEventListener('DOMContentLoaded', async function() {
    renderListAppointments();
});