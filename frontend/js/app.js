const form = document.getElementById("appointment-form");
const message = document.getElementById("message");
const barberSelect = document.getElementById("barberId");
const serviceSelect = document.getElementById("serviceId");
const list = document.getElementById("appointments-list");

async function loadOptions() {
    const [barbersRes, servicesRes] = await Promise.all([
        fetch("/api/barbers"),
        fetch("/api/services")
    ]);

    const barbers = await barbersRes.json();
    const services = await servicesRes.json();

    barberSelect.innerHTML = barbers
        .map((barber) => `<option value="${barber.id}">${barber.name} - ${barber.specialty}</option>`)
        .join("");

    serviceSelect.innerHTML = services
        .map((service) => `<option value="${service.id}">${service.name} ($${service.price})</option>`)
        .join("");
}

function setMessage(text, isError = false) {
    message.textContent = text;
    message.className = isError ? "status-error" : "status-ok";
}

function formatAppointment(item) {
    return `<li>
        <strong>${item.clientName}</strong>
        ${item.appointmentDate} ${item.appointmentTime.substring(0, 5)}<br/>
        ${item.barberName} | ${item.serviceName}
    </li>`;
}

async function loadAppointments() {
    const response = await fetch("/api/appointments");
    const appointments = await response.json();

    if (!appointments.length) {
        list.innerHTML = "<li>No hay citas aun.</li>";
        return;
    }

    list.innerHTML = appointments
        .sort((a, b) => `${a.appointmentDate} ${a.appointmentTime}`.localeCompare(`${b.appointmentDate} ${b.appointmentTime}`))
        .map(formatAppointment)
        .join("");
}

form.addEventListener("submit", async (event) => {
    event.preventDefault();

    const payload = {
        clientName: document.getElementById("clientName").value.trim(),
        clientEmail: document.getElementById("clientEmail").value.trim(),
        barberId: Number(barberSelect.value),
        serviceId: Number(serviceSelect.value),
        appointmentDate: document.getElementById("appointmentDate").value,
        appointmentTime: document.getElementById("appointmentTime").value
    };

    const response = await fetch("/api/appointments", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
    });

    if (!response.ok) {
        const errorData = await response.json().catch(() => ({}));
        setMessage(errorData.message || "No se pudo guardar la cita.", true);
        return;
    }

    form.reset();
    setMessage("Cita registrada correctamente.");
    await loadAppointments();
});

async function bootstrap() {
    await loadOptions();
    await loadAppointments();
}

bootstrap().catch(() => {
    setMessage("No se pudo cargar la informacion inicial.", true);
});
