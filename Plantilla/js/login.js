$(document).ready(function() {
  
    var animating = false,
        submitPhase1 = 1100,
        submitPhase2 = 400,
        logoutPhase1 = 800,
        $login = $(".login"),
        $app = $(".app");
    
    function ripple(elem, e) {
      $(".ripple").remove();
      var elTop = elem.offset().top,
          elLeft = elem.offset().left,
          x = e.pageX - elLeft,
          y = e.pageY - elTop;
      var $ripple = $("<div class='ripple'></div>");
      $ripple.css({top: y, left: x});
      elem.append($ripple);
    };

    function pic() {
        var bgm = ['https://i.pinimg.com/originals/52/d8/39/52d83925edc943ef42cce83cac003645.jpg',
        'https://cutewallpaper.org/25/anime-wallpaper-no-game-no-life-minimalist/shiro-b3e33-minimalist-21f71-wallpaper-f8fed-rnogamenolife.png',
        'https://i.pinimg.com/originals/ee/b8/f6/eeb8f61c0d096977c8883436bb27f414.jpg',
        'https://i.pinimg.com/originals/e3/e0/8c/e3e08cd203c41144832a91dc2c286a29.jpg',
        'https://wallpaperaccess.com/full/1407671.png',
        'https://cdna.artstation.com/p/assets/images/images/047/527/210/large/luiz-santos-luffy-prancheta-1.jpg?1647816858',
        'https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/944a540a-5068-4583-be10-4ba056c879b6/ddysx8q-af9ad00f-c406-4964-b8b9-f03c22af840f.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzk0NGE1NDBhLTUwNjgtNDU4My1iZTEwLTRiYTA1NmM4NzliNlwvZGR5c3g4cS1hZjlhZDAwZi1jNDA2LTQ5NjQtYjhiOS1mMDNjMjJhZjg0MGYucG5nIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.R5wobbmBD2Q4PkDtLbL82gtCrIzGyYyQnlA94xd_rmQ',
        'https://cutewallpaper.org/25/anime-minimalist-mobile-wallpaper-reddit/29-minimalist-anime-wallpaper-reddit-baka-wallpaper.png',
        'https://images2.alphacoders.com/961/961300.png',
        'https://preview.redd.it/j18zkr2ec5b11.png?auto=webp&s=e67224d47d8118ec5000f0d459721df1e599248a',
    ];
     
        $('body').css({
            'background' : 'url('+ bgm[Math.floor(Math.random() * bgm.length)] + ') no-repeat',
        });
    }
    pic();
    
    $(document).on("click", ".login__submit", function(e) {
      if (animating) return;
      animating = true;
      var that = this;
      ripple($(that), e);
      $(that).addClass("processing");
      setTimeout(function() {
        $(that).addClass("success");
        setTimeout(function() {
          $app.show();
          $app.css("top");
          $app.addClass("active");
        }, submitPhase2 - 70);
        setTimeout(function() {
          $login.hide();
          $login.addClass("inactive");
          animating = false;
          $(that).removeClass("success processing");
        }, submitPhase2);
      }, submitPhase1);
    });
    
    $(document).on("click", ".app__logout", function(e) {
      if (animating) return;
      $(".ripple").remove();
      animating = true;
      var that = this;
      $(that).addClass("clicked");
      setTimeout(function() {
        $app.removeClass("active");
        $login.show();
        $login.css("top");
        $login.removeClass("inactive");
      }, logoutPhase1 - 120);
      setTimeout(function() {
        $app.hide();
        animating = false;
        $(that).removeClass("clicked");
      }, logoutPhase1);
    });
    
  });