/* Template Name: Doctris - Doctor Appointment Booking System
 Author: Shreethemes
 Website: https://shreethemes.in/
 Mail: support@shreethemes.in
 Version: 1.0.0
 Updated: July 2021
 File Description: Main JS file of the template
 */

/*********************************/
/*         INDEX                 */
/*================================
 *     01.  Loader               *
 *     02.  Toggle Menus         *
 *     03.  Active Menu          *
 *     04.  Clickable Menu       *
 *     05.  Back to top          *
 *     06.  Feather icon         *
 *     06.  DD Menu              *
 *     06.  Active Sidebar Menu  *
 ================================*/


window.onload = function loader() {
    // Preloader
    setTimeout(() => {
        document.getElementById('preloader').style.visibility = 'hidden';
        document.getElementById('preloader').style.opacity = '0';
    }, 50);

    // Menus
    activateMenu();
    activateSidebarMenu();
}

$('th').click(function () {
    var table = $(this).parents('table').eq(0)
    var rows = table.find('tr:gt(0)').toArray().sort(comparer($(this).index()))
    this.asc = !this.asc
    if (!this.asc) {
        rows = rows.reverse()
    }
    for (var i = 0; i < rows.length; i++) {
        table.append(rows[i])
    }
})
function comparer(index) {
    return function (a, b) {
        var valA = getCellValue(a, index), valB = getCellValue(b, index)
        return $.isNumeric(valA) && $.isNumeric(valB) ? valA - valB : valA.toString().localeCompare(valB)
    }
}
function getCellValue(row, index) {
    return $(row).children('td').eq(index).text()
}

//Menu
// Toggle menu
function toggleMenu() {
    document.getElementById('isToggle').classList.toggle('open');
    var isOpen = document.getElementById('navigation')
    if (isOpen.style.display === "block") {
        isOpen.style.display = "none";
    } else {
        isOpen.style.display = "block";
    }
}
;

//Menu Active
function getClosest(elem, selector) {

    // Element.matches() polyfill
    if (!Element.prototype.matches) {
        Element.prototype.matches =
                Element.prototype.matchesSelector ||
                Element.prototype.mozMatchesSelector ||
                Element.prototype.msMatchesSelector ||
                Element.prototype.oMatchesSelector ||
                Element.prototype.webkitMatchesSelector ||
                function (s) {
                    var matches = (this.document || this.ownerDocument).querySelectorAll(s),
                            i = matches.length;
                    while (--i >= 0 && matches.item(i) !== this) {
                    }
                    return i > -1;
                };
    }

    // Get the closest matching element
    for (; elem && elem !== document; elem = elem.parentNode) {
        if (elem.matches(selector))
            return elem;
    }
    return null;

}
;

function activateMenu() {
    var menuItems = document.getElementsByClassName("sub-menu-item");
    if (menuItems) {

        var matchingMenuItem = null;
        for (var idx = 0; idx < menuItems.length; idx++) {
            if (menuItems[idx].href === window.location.href) {
                matchingMenuItem = menuItems[idx];
            }
        }

        if (matchingMenuItem) {
            matchingMenuItem.classList.add('active');
            var immediateParent = getClosest(matchingMenuItem, 'li');
            if (immediateParent) {
                immediateParent.classList.add('active');
            }

            var parent = getClosest(matchingMenuItem, '.parent-menu-item');
            if (parent) {
                parent.classList.add('active');
                var parentMenuitem = parent.querySelector('.menu-item');
                if (parentMenuitem) {
                    parentMenuitem.classList.add('active');
                }
                var parentOfParent = getClosest(parent, '.parent-parent-menu-item');
                if (parentOfParent) {
                    parentOfParent.classList.add('active');
                }
            } else {
                var parentOfParent = getClosest(matchingMenuItem, '.parent-parent-menu-item');
                if (parentOfParent) {
                    parentOfParent.classList.add('active');
                }
            }
        }
    }
}


//Admin Menu
function activateSidebarMenu() {
    var current = location.pathname.substring(location.pathname.lastIndexOf('/') + 1);
    if (current !== "" && document.getElementById("sidebar")) {
        var menuItems = document.querySelectorAll('#sidebar a');
        for (var i = 0, len = menuItems.length; i < len; i++) {
            if (menuItems[i].getAttribute("href").indexOf(current) !== -1) {
                menuItems[i].parentElement.className += " active";
                if (menuItems[i].closest(".sidebar-submenu")) {
                    menuItems[i].closest(".sidebar-submenu").classList.add("d-block");
                }
                if (menuItems[i].closest(".sidebar-dropdown")) {
                    menuItems[i].closest(".sidebar-dropdown").classList.add("active");
                }
            }
        }
    }
}

if (document.getElementById("close-sidebar")) {
    document.getElementById("close-sidebar").addEventListener("click", function () {
        document.getElementsByClassName("page-wrapper")[0].classList.toggle("toggled");
    });
}

// Clickable Menu
if (document.getElementById("navigation")) {
    var elements = document.getElementById("navigation").getElementsByTagName("a");
    for (var i = 0, len = elements.length; i < len; i++) {
        elements[i].onclick = function (elem) {
            if (elem.target.getAttribute("href") === "javascript:void(0)") {
                var submenu = elem.target.nextElementSibling.nextElementSibling;
                submenu.classList.toggle('open');
            }
        }
    }
}

if (document.getElementById("sidebar")) {
    var elements = document.getElementById("sidebar").getElementsByTagName("a");
    for (var i = 0, len = elements.length; i < len; i++) {
        elements[i].onclick = function (elem) {
            if (elem.target.getAttribute("href") === "javascript:void(0)") {
                elem.target.parentElement.classList.toggle("active");
                elem.target.nextElementSibling.classList.toggle("d-block");
            }
        }
    }
}

// Menu sticky
function windowScroll() {
    var navbar = document.getElementById("topnav");
    if (navbar === null) {

    } else if (document.body.scrollTop >= 50 ||
            document.documentElement.scrollTop >= 50) {
        navbar.classList.add("nav-sticky");
    } else {
        navbar.classList.remove("nav-sticky");
    }
}

window.addEventListener('scroll', (ev) => {
    ev.preventDefault();
    windowScroll();
})

// back-to-top
window.onscroll = function () {
    scrollFunction();
};

function scrollFunction() {
    var mybutton = document.getElementById("back-to-top");
    if (mybutton === null) {

    } else if (document.body.scrollTop > 500 || document.documentElement.scrollTop > 500) {
        mybutton.style.display = "block";
    } else {
        mybutton.style.display = "none";
    }
}

function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

//Feather icon
feather.replace();

// dd-menu
if (document.getElementsByClassName("dd-menu")) {
    var ddmenu = document.getElementsByClassName("dd-menu");
    for (var i = 0, len = ddmenu.length; i < len; i++) {
        ddmenu[i].onclick = function (elem) {
            elem.stopPropagation();
        }
    }
}

//ACtive Sidebar
(function () {
    var current = location.pathname.substring(location.pathname.lastIndexOf('/') + 1);
    ;
    if (current === "")
        return;
    var menuItems = document.querySelectorAll('.sidebar-nav a');
    for (var i = 0, len = menuItems.length; i < len; i++) {
        if (menuItems[i].getAttribute("href").indexOf(current) !== -1) {
            menuItems[i].parentElement.className += " active";
        }
    }
})();


//Validation Shop Checkouts
(function () {
    'use strict'

    if (document.getElementsByClassName('needs-validation').length > 0) {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.querySelectorAll('.needs-validation')

        // Loop over them and prevent submission
        Array.prototype.slice.call(forms)
                .forEach(function (form) {
                    form.addEventListener('submit', function (event) {
                        if (!form.checkValidity()) {
                            event.preventDefault()
                            event.stopPropagation()
                        }

                        form.classList.add('was-validated')
                    }, false)
                })
    }
})();

//Tooltip
var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl)
});



function CheckFullName(text) {
    const value = text.value.trim();

    // Kiểm tra khoảng trắng đầu chuỗi
    if (/^\s/.test(text.value)) {
        text.setCustomValidity('Không được bắt đầu bằng khoảng trắng.');
    } else {
        // Regex kiểm tra từ 2 đến 30 từ, mỗi từ chỉ chứa chữ, cách nhau đúng 1 dấu cách
        const fullNameRegex = /^([a-zA-ZÀ-ỹ]+)( [a-zA-ZÀ-ỹ]+){1,29}$/;

        if (!fullNameRegex.test(value)) {
            text.setCustomValidity('Chỉ chứa chữ cái và cách nhau bằng 1 khoảng trắng.');
        } else {
            text.setCustomValidity('');
        }
    }

    text.reportValidity();
    return true;
}

function CheckTitle(text) {
    const value = text.value;

    if (/^\s/.test(value)) {
        text.setCustomValidity('Không được bắt đầu bằng khoảng trống');
    } else {
        const title = /^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễếệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]{14,}(?:[a-zA-ZÀÁÂÃ...]+){0,2}$/;
        if (!title.test(value.trim())) {
            text.setCustomValidity('Title không hợp lệ.');
        } else {
            text.setCustomValidity('');
        }
    }
    text.reportValidity();
    return true;
}

function CheckCCCD(text) {
    const value = text.value;

    if (/^\s/.test(value)) {
        text.setCustomValidity('Không được bắt đầu bằng khoảng trống.');
    } else {
        const cccd = value.trim();
        if (!/^\d+$/.test(cccd)) {
            text.setCustomValidity('Mã đinh danh/ CCCD chỉ được chứa chữ số.');
        } else if (cccd.length !== 9 && cccd.length !== 12) {
            text.setCustomValidity('Mã định danh/ CCCD phải có đúng 9 hoặc 12 chữ số.');
        } else {
            text.setCustomValidity('');
        }
    }
    text.reportValidity();
    return true;
}

function CheckMaxLength50(text) {
    const value = text.value;

    // Kiểm tra khoảng trắng ở đầu
    if (/^\s/.test(value)) {
        text.setCustomValidity('Không được có khoảng trắng ở đầu.');
    }
    // Kiểm tra độ dài tối đa
    else if (value.length > 50) {
        text.setCustomValidity("Vui lòng nhập tối đa 50 ký tự.");
    }
    else {
        text.setCustomValidity('');
    }

    text.reportValidity();
    return true;
}

function CheckMaxLength200(text) {
    const value = text.value;

    // Kiểm tra khoảng trắng ở đầu
    if (/^\s/.test(value)) {
        text.setCustomValidity('Không được có khoảng trắng ở đầu.');
    }
    // Kiểm tra độ dài tối đa
    else if (value.length > 200) {
        text.setCustomValidity("Vui lòng nhập không quá 200 ký tự.");
    }
    else {
        text.setCustomValidity('');
    }

    text.reportValidity();
    return true;
}



function CheckBHYT(text) {
    const value = text.value;

    if (value.trim() === "") {
        text.setCustomValidity('');
    } else if (/^\s/.test(value)) {
        text.setCustomValidity('Không được bắt đầu bằng khoảng trống.');
    } else {
        const bhyt = value.trim();
        if (!/^\d{10}$/.test(bhyt)) {
            text.setCustomValidity('Mã BHYT không hợp lệ.');// phải đúng 10 chữ số
        } else {
            text.setCustomValidity('');
        }
    }

    text.reportValidity();
    return true;
}

function CheckPhone(text) {
    const value = text.value;

    if (/^\s/.test(value)) {
        text.setCustomValidity('Không được bắt đầu bằng khoảng trống.');
    } else {
        const phone = /(84|0[3|5|7|8|9])+([0-9]{8})\b/;
        if (!phone.test(value.trim())) {
            text.setCustomValidity('Số điện thoại không hợp lệ');
        } else {
            text.setCustomValidity('');
        }
    }
    text.reportValidity();
    return true;
}

function CheckPrice(text) {
    var phone = /([0-9.]{5,})\b/;
    if (!phone.test(text.value)) {
        text.setCustomValidity('Giá không hợp lệ');
    } else {
        text.setCustomValidity('');
    }
    return true;
}



function CheckUserName(text) {
    const value = text.value;

    if (/^\s/.test(value)) {
        text.setCustomValidity('Tên đăng nhập không được bắt đầu bằng khoảng trống.');
    } else {
        const username = /^(?!.*\.\.)(?!.*\.$)[^\W][\w.]{0,29}$/;
        if (!username.test(value)) {
            text.setCustomValidity('Tên đăng nhập không hợp lệ.');
        } else {
            text.setCustomValidity('');
        }
    }

    text.reportValidity();


    return true;
}

function CheckEmail(text) {
    const value = text.value;
    if (/^\s/.test(value)) {
        text.setCustomValidity('Không được bắt đầu bằng khoảng trắng.');
    } else {
        // Biểu thức kiểm tra email hợp lệ
        const email = /^[\w.-]+@([\w-]+\.)+[\w-]{2,4}$/;
        if (!email.test(value.trim())) {
            text.setCustomValidity('Email không hợp lệ.');
        } else {
            text.setCustomValidity('');
        }
    }

    text.reportValidity();
    return true;
}

function CheckPassword(text) {
    const value = text.value;

    if (/^\s/.test(value)) {
        text.setCustomValidity('Mật khẩu chứa ít nhất 8 ký tự, gồm chữ hoa, thường, số và ký tự đặc biệt.');
    } else {
        const pass = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/;
        if (!pass.test(value)) {
            text.setCustomValidity('Mật khẩu chứa ít nhất 8 ký tự, gồm chữ hoa, thường, số và ký tự đặc biệt.');
        } else {
            text.setCustomValidity('');
        }
    }
    text.reportValidity();
    return true;
}

function CheckRePassword(text) {
    const value = text.value;

    const password = document.getElementById('password').value;
    if (password !== value) {
        text.setCustomValidity('Mật khẩu không khớp!');
    } else {
        text.setCustomValidity('');

    }
    text.reportValidity();
    return true;
}


function CheckNumber(text) {
    var number = /([0-9])+/;
    if (!number.test(text.value)) {
        text.setCustomValidity('Sai định dạng');
    } else if (text.value < 0) {
        text.setCustomValidity('Yêu cầu lớn hơn 0');
    } else {
        text.setCustomValidity('');
    }
    return true;
}
