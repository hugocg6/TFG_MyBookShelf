$(function() {

    $('.js-check-all').on('click', function() {
  
        if ( $(this).prop('checked') ) {
            $('th input[type="checkbox"]').each(function() {
                $(this).prop('checked', true);
          $(this).closest('tr').addClass('active');
            })
        } else {
            $('th input[type="checkbox"]').each(function() {
                $(this).prop('checked', false);
          $(this).closest('tr').removeClass('active');
            })
        }
        updateReadingProgress();
    });
  
    $('th[scope="row"] input[type="checkbox"]').on('click', function() {
      if ( $(this).closest('tr').hasClass('active') ) {
        $(this).closest('tr').removeClass('active');
          const bookId = $(this).attr('id').split('-')[2];
          markAsUnread(bookId);
      } else {
        $(this).closest('tr').addClass('active');
          const bookId = $(this).attr('id').split('-')[2];
          markAsRead(bookId);
      }
      updateReadingProgress();
    });
});

function markAsRead(bookId) {
    const csrfToken = $("meta[name='_csrf']").attr("content");
    const csrfHeader = $("meta[name='_csrf_header']").attr("content");

    const headers = {};
    headers[csrfHeader] = csrfToken;

    $.ajax({
        url: '/book/' + bookId + '/markRead',
        type: 'POST',
        headers: headers,
        data: {bookId: bookId},
        success: function (response) {
            console.log("Book marked as read successfully.");
            updateReadingProgress();
            if (response.readDate) {
                const readDateElement = document.querySelector('.stats-item .read-date');
                readDateElement.textContent = response.readDate;
            }
        },
        error: function (xhr, status, error) {
            console.error("Error marking book as read:", error);
        }
    });
}

function markAsUnread(bookId) {
    const csrfToken = $("meta[name='_csrf']").attr("content");
    const csrfHeader = $("meta[name='_csrf_header']").attr("content");

    const headers = {};
    headers[csrfHeader] = csrfToken;

    $.ajax({
        url: '/book/' + bookId + '/unmarkRead',
        type: 'POST',
        headers: headers,
        data: {bookId: bookId},
        success: function (response) {
            console.log("Book marked as read successfully.");
            updateReadingProgress();
            if (response.readDate) {
                document.querySelector('.stats-item .read-date').textContent = response.readDate;
            }
        },
        error: function (xhr, status, error) {
            console.error("Error marking book as read:", error);
        }
    });
}

function updateReadingProgress() {
    const svgItem = document.querySelector('.svg-item');
    const totalBooks = parseInt(svgItem.getAttribute('data-total-books'));
    let readBooks = 0;

    $('.read-checkbox').each(function() {
        if ($(this).prop('checked')) {
            readBooks++;
        }
    });

    const percentage = totalBooks === 0 ? 0 : Math.round((readBooks / totalBooks) * 100);

    const donutSegment = svgItem.querySelector('.donut-segment-2');
    const circumference = 2 * Math.PI * 15.91549430918954;
    const offset = circumference - (percentage / 100) * circumference;
    donutSegment.style.strokeDasharray = `${circumference} ${circumference}`;
    donutSegment.style.strokeDashoffset = offset;

    const percentText = svgItem.querySelector('.donut-percent');
    percentText.textContent = `${percentage}%`;

    const dataText = svgItem.querySelector('.donut-data');
    dataText.textContent = `${readBooks}/${totalBooks} read`;
}

document.addEventListener('DOMContentLoaded', function () {
    updateReadingProgress()
});