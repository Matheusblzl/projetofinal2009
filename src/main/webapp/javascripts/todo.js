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
	// $("#tarefa").on("keydown.primeiro", function() {
	// console.log("Esse é o primeiro evento");
	// });
	//
	// $("#tarefa").on("keydown.segundo", function() {
	// console.log("Esse é o segundo evento");
	// });
	//
	// $("#tarefa").off("keydown.primeiro");
});

$(function() {

	var $lastClicked;

	// ======= Remover =======
	function onTarefaDeleteClick() {
		// console.log($(this).parent('.tarefa-item').text().trim());
		// $(this).parent('.tarefa-item').hide();
		$(this).parent('.tarefa-item').off('click').hide('slow', function() {
			$(this).remove();
		});
	}


	// ======= EDIT =======

	function onTarefaItemClick() {
		if (!$(this).is($lastClicked)) {
			if ($lastClicked !== undefined) {
				savePendingEdition($lastClicked);
			}

			$lastClicked = $(this);

			var text = $lastClicked.children('.tarefa-texto').text();
			var html = "<input type='text' class='tarefa-edit' value='" + text
					+ "'>";

			$lastClicked.html(html);

			$(".tarefa-edit").keydown(onTarefaEditKeydown);
		}
	}

	function onTarefaEditKeydown(event) {
		if (event.which === 13) {
			savePendingEdition($lastClicked);
			$lastClicked = undefined;
		}
	}

	function savePendingEdition($tarefa) {
		var text = $tarefa.children('.tarefa-edit').val();
		
		$tarefa.empty();
		
//		$tarefa.append("<div class='tarefa-texto'>" + text + "</div>")
//			.append("<div class='tarefa-delete'></div>")
//			.append("<div class='clear'></div>");
		
		$tarefa.append($("<div />")
				.addClass("tarefa-texto")
				.text(text))
				.append($("<div />")
				.addClass("tarefa-delete"))
				.append($("<div />")
				.addClass("clear"));
		
		$(".tarefa-delete").click(onTarefaDeleteClick);
		
		$tarefa.click(onTarefaItemClick);
	}

	
	// ==== ADICIONAR ====
	function onTarefaKeydown(event) {
		if (event.which === 13) {
			addTarefa($("#tarefa").val());
			$("#tarefa").val("");
		}
	}

		
	  function addTarefa(text) {
	    var $tarefa = $("<div />")
	                  .addClass("tarefa-item")
	                  .append($("<div />")
	                          .addClass("tarefa-texto")
	                          .text(text))
	                  .append($("<div />")
	                          .addClass("tarefa-delete"))
	                  .append($("<div />")
	                          .addClass("clear"));

	    $("#tarefa-list").append($tarefa);

	    $(".tarefa-delete").click(onTarefaDeleteClick);

	    $(".tarefa-item").click(onTarefaItemClick);
	  }
	  
	  
	  
	  $(".tarefa-delete").click(onTarefaDeleteClick);
	  $(".tarefa-item").click(onTarefaItemClick);
	  $("#tarefa").keydown(onTarefaKeydown);
});