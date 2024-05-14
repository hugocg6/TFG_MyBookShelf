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
        },
        error: function (xhr, status, error) {
            console.error("Error marking book as read:", error);
        }
    });
}