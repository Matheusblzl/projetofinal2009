$(function() {
	/*
	 * $('#tarefa').keydown(function(event) { console.log(event.which,
	 * String.fromCharCode(event.which)); }); $('#tarefa').off();
	 * $('#tarefa').keydown(function(event) { if (event.which === 13) {
	 * console.log("Aqui vamos adicionar nossa tarefa"); } });
	 */

	// Cancelando evento desejado.
	// $("#tarefa").keydown(function() {
	// console.log("Esse é o primeiro evento");
	// });
	// $("#tarefa").keydown(function() {
	// console.log("Esse é o segundo evento");
	// });
	
	$("#tarefa").on("keydown.primeiro", function() {
		console.log("Esse é o primeiro evento");
	});
	$("#tarefa").on("keydown.segundo", function() {
		console.log("Esse é o segundo evento");
	});
	
	$("#tarefa").off("keydown.primeiro");


});
