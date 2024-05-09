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
      } else {
        $(this).closest('tr').addClass('active');
      }
    });
  });

function toggleCollection() {
    const button = document.getElementById("add-collection-button");

    if (button.textContent.trim() === "+ Add") {
        button.textContent = "Added";
        button.classList.add("c-button--added");

        const form = document.getElementById("add-collection-form");
        form.submit();
    } else {
        button.textContent = "+ Add";
        button.classList.remove("c-button--added");
    }
}