$(document).ready(function () {
        $("#menu > li > div").click(function () {
 
            if (false == $(this).next().is(':visible')) {
                $('#menu ul').slideUp(300);
            }
            $(this).next().slideToggle(300);
        });
        $('#menu ul:eq(0)').show();
 
    });
	

$(document).ready(function() {
	$("#grid tr:even").addClass('classEven');
});


$(document).ready(function () {
	  $("span.tooltip_message").hover(function () {
	    $(this).append('<div class="message"><p>Wyszukaj za pomocą słowa kluczowego w:<ul><li>Imię autora </li><li>Nazwisko autora <li>Tytuł książki </li></ul></p></div>');
	  },function () {
	    $("div.message").remove();
	  });
	  $("span.tooltip_img1").hover(function(){$(this).append('<div class="message"><ul><li>Tytuł - Beginning Groovy, Grails and Griffon</li><li>Autor: Vishal Layka</li><li>Wydawca: Apress</li></ul></div>');
}, function(){$("div.message").remove();});
	});
