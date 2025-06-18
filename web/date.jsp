<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8" />
    <title>L?ch tháng 6/2025</title>

    <link href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/locales/vi.global.min.js"></script>

    <style>
        body {
            /* Nút trái & ph?i: hình tròn có m?i tên */
            .fc-prev-button, .fc-next-button {
                width: 36px;
                height: 36px;
                border-radius: 50%;
                padding: 0;
                font-size: 18px;
                line-height: 1;
                background-color: #0077b6;
                color: white;
                border: none;
                display: flex;
                align-items: center;
                justify-content: center;
                transition: background-color 0.2s ease;
            }

            .fc-prev-button:hover, .fc-next-button:hover {
                background-color: #005f87;
            }

            /* Thay ch? b?ng bi?u t??ng m?i tên */
            .fc-prev-button::before {
                content: "\25C0"; /* ? */
            }

            .fc-next-button::before {
                content: "\25B6"; /* ? */
            }

            /* ?n ch? trong nút g?c */
            .fc-prev-button .fc-icon, .fc-next-button .fc-icon {
                display: none;
            }

            font-family: Arial, sans-serif;
            background: #f0f2f5;
            padding: 20px;
            display: flex;
            justify-content: center;
        }

        #calendar {
            width: 100%;         /* full chi?u ngang */
            max-width: 900px;     /* b? gi?i h?n max-width */
            background: #fff;
            border: 2px solid #ccc;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        /* ??t chi?u cao và chi?u r?ng c? ??nh cho ô tiêu ?? th? và ô ngày */
        .fc-col-header-cell, /* ô ch?a ch? th? */
        .fc-daygrid-day {
            font-size: 20px;
            width: 14.28%; /* chia ??u 7 c?t */
            height: 90px;  /* chi?u cao c? ??nh cho c? tiêu ?? và ngày */
            box-sizing: border-box;
            position: relative; /* ?? dùng cho pseudo-element */
        }

        /* Ô ch? th? to b?ng ô ch?a ngày */
        .fc-col-header-cell-cushion {
            font-size: 18px;
            font-weight: 700;
            line-height: 90px; /* canh gi?a theo chi?u cao */
            text-align: center;
            user-select: none;
            color: #333;
        }

        /* Canh gi?a s? ngày trong ô */
        .fc-daygrid-day-frame {
            height: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-weight: 600;
            font-size: 20px;
            color: inherit;
            cursor: pointer;
        }

        /* Nút và tiêu ?? */
        .fc-toolbar-title {
            font-size: 20px;
            font-weight: bold;
            color: #0077b6;
            margin: 0 12px;
        }

        /* Màu ch? Ch? nh?t ??, th? B?y cam */
        .fc-col-header-cell.fc-day-sun .fc-col-header-cell-cushion {
            color: #e74c3c !important;
            font-weight: bold;
        }

        .fc-col-header-cell.fc-day-sat .fc-col-header-cell-cushion {
            color: #f39c12 !important;
            font-weight: bold;
        }

        /* Vi?n ki?u v? ô ly */
        .fc .fc-daygrid-day-frame {
            background-color: #fff;
            transition: background-color 0.3s ease;
        }

        /* Vi?n ph?i cho c?t cu?i (Th? B?y) */
        .fc-daygrid-day:last-child .fc-daygrid-day-frame {
            border-right: none;
        }

        /* Vi?n d??i cho hàng cu?i */
        .fc-daygrid-body tr:last-child td .fc-daygrid-day-frame {
            border-bottom: none;
        }

        /* Header c?ng vi?n d??i ?? phân cách */
        .fc-col-header-cell {
            border-bottom: 1px solid #bbb;
            border-right: 1px solid #ddd;
            background-color: #fafafa;
        }

        /* Không vi?n ph?i cho header th? 7 */
        .fc-col-header-cell.fc-day-sat {
            border-right: none;
        }

        /* Ngày hôm nay */
        .fc-day-today .fc-daygrid-day-frame {
            border: 2px solid #d63031 !important;
            background-color: #ffeaa7;
        }

        /* M? ngày ngoài tháng */
        .fc-daygrid-day.fc-day-other .fc-daygrid-day-frame {
            color: #999 !important;
            background-color: #f9f9f9 !important;
            cursor: default;
            pointer-events: none;
        }

        /* Hover ngày: hi?n background nh? 1/3 ô */
        .fc-daygrid-day-frame:hover::after {
            content: "";
            position: absolute;
            top: 50%;
            left: 50%;
            width: 50%;  /* kho?ng 1/3 ô */
            height: 50%;
            background-color: rgba(0, 123, 255, 0.3);
            border-radius: 50%; /* hình tròn */
            transform: translate(-50%, -50%);
            pointer-events: none; /* không ch?n s? ki?n chu?t */
            transition: background-color 0.3s ease;
            z-index: 10;
        }

        /* Nút toolbar */
        .fc-button {
            font-weight: 600;
            font-size: 14px;
            padding: 6px 12px;
            cursor: pointer;
        }

        /* Toolbar layout */
        .fc-header-toolbar {
            display: flex !important;
            justify-content: center !important;
            align-items: center !important;
            gap: 6px;
        }

        .fc-header-toolbar > div {
            flex: 0 0 auto;
            display: flex;
            align-items: center;
        }

        .fc-header-toolbar .fc-toolbar-chunk {
            gap: 6px;
        }

        /* Text ngày ch?n */
        .clicked-date {
            margin-top: 20px;
            font-size: 16px;
            color: #0077b6;
            font-weight: bold;
            text-align: center;
        }
    </style>
</head>
<body>

    <div>
        <div id="calendar"></div>
        <div class="clicked-date" id="clicked-date">Hãy ch?n m?t ngày</div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const calendarEl = document.getElementById('calendar');
            const clickedDateEl = document.getElementById('clicked-date');

            const calendar = new FullCalendar.Calendar(calendarEl, {
                locale: 'vi',
                initialView: 'dayGridMonth',
                initialDate: '2025-06-01',
                headerToolbar: {
                    left: 'prev',
                    center: 'title',
                    right: 'next'
                },
                firstDay: 0,
                height: 'auto',
                dayHeaderFormat: { weekday: 'short' },
                dayHeaderContent: function (arg) {
                    const mapThu = {
                        0: 'CN',
                        1: 'Hai',
                        2: 'Ba',
                        3: 'T\u01B0',
                        4: 'N\u0103m',
                        5: 'S\u00E1u',
                        6: 'B\u1EA3y'
                    };
                    const dayNum = arg.date.getDay();
                    const text = mapThu[dayNum] || '';

                    let span = document.createElement('span');
                    span.textContent = text;
                    return { domNodes: [span] };
                },
                dayCellContent: function(arg) {
                    // Hi?n th? ngày luôn 2 ch? s?
                    let day = arg.date.getDate();
                    let dayText = day < 10 ? '0' + day : String(day);
                    let span = document.createElement('span');
                    span.textContent = dayText;
                    return { domNodes: [span] };
                },
                dateClick: function (info) {
                    const clickedDate = info.date;
                    const calendarDate = calendar.getDate();
                    if (clickedDate.getMonth() !== calendarDate.getMonth()) {
                        return;
                    }
                    const vnDate = clickedDate.toLocaleDateString('vi-VN', { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' });
                    clickedDateEl.textContent = `B?n ?ã ch?n: ${vnDate}`;
                }
            });

            calendar.render();
        });
    </script>
</body>
</html>
