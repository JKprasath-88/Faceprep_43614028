
document.getElementById("addEmployeeById").addEventListener("submit", function(event) {
    event.preventDefault();

    let name = document.getElementById("fname").value;
    let age = document.getElementById("age").value;
    let dob = document.getElementById("dob").value;
    let position = document.getElementById("position").value;
    let gender = document.querySelector('input[name="gender"]:checked')?.value || "Not specified";

    let table = document.getElementById("employeeTable").querySelector("tbody");
    let row = document.createElement("tr");

    row.innerHTML = `
        <td>${name}</td>
        <td>${age}</td>
        <td>${dob}</td>
        <td>${position}</td>
        <td>${gender}</td>
    `;

    table.appendChild(row);
    this.reset();
});

document.getElementById("removeBtn").addEventListener("click", function() {
    let table = document.getElementById("employeeTable").querySelector("tbody");
    if (table.rows.length > 0) {
        table.deleteRow(-1);
    } else {
        alert("No rows to remove!");
    }
});
