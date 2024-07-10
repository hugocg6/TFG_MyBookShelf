$(function(){
    var $ppc = $('.progress-pie-chart'),
        percent = parseInt($ppc.data('percent')),
        deg = 360*percent/100;
    if (percent > 50) {
        $ppc.addClass('gt-50');
    }
    $('.ppc-progress-fill').css('transform','rotate('+ deg +'deg)');
    $('.ppc-percents span').html(percent+'%');
});


document.addEventListener('DOMContentLoaded', function() {
    const currentMonth = new Date().getMonth();
    const monthGroups = document.querySelectorAll('.month-group');
    monthGroups.forEach(function(monthGroup) {
        if (parseInt(monthGroup.getAttribute('data-month')) === currentMonth) {
            monthGroup.classList.add('selected');
        }
    });
});