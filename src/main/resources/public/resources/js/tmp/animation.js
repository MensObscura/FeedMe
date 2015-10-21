
$(window).scroll(function( about ) {
    $('#about-animation').each(function(about){
    var imagePos = $(this).offset().top;

    var topOfWindow = $(window).scrollTop();
      if (imagePos < topOfWindow+600) {
        $(this).addClass("expandUp");
      }
    });
  });

$(window).scroll(function( about ) {
    $('#who-v-animation').each(function(about){
    var imagePoss = $(this).offset().top;

    var topOfWindoww = $(window).scrollTop();
      if (imagePoss < topOfWindoww+1500) {
        $(this).addClass("expandUp");
      }
    });
  });

