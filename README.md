Приложение содержит 3 Activity:

1. отображает кнопку, по нажатию на которую, осуществляется загрузка из сервиса данных в формате json и открытие новой activity, если загрузка прошла успешно

2. отображает activity со списком полученных элементов.
Если нажать и долго удерживать на элемент, появится всплывающее окно с детальной информацией. То есть, в этом случае, новая активити не открывается.

3. отображает детальную информацию по кликнутому элементу


Формат получаемого json:
[
  {
    "id": 1,
    "first_name": "Charlean",
    "last_name": "Womersley",
    "email": "cwomersley0@addthis.com",
    "gender": "Female",
    "ip_address": "103.173.100.174"
  }
  , ...
]