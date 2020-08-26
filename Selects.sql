--1. Работа с типами данных Date, NULL значениями, трехзначная логика. Возвращение определенных значений в результатах запроса
--в зависимости от полученных первоначальных значений результата запроса. Высветка в результатах запроса только определенных колонок.

--1. Выбрать в таблице Orders заказы, которые были доставлены после 5 мая 1998 года (колонка ShippedDate) включительно
--и которые доставлены с ShipVia >= 2. Формат указания даты должен быть верным при любых региональных настройках.
--Этот метод использовать далее для всех заданий. Запрос должен высвечивать только колонки OrderID, ShippedDate и ShipVia.
--Пояснить почему сюда не попали заказы с NULL-ом в колонке ShippedDate.
select OrderID, ShippedDate, ShipVia
from Orders
where ShippedDate >= '5-05-1998' and ShipVia >= 2;

--2. Написать запрос, который выводит только недоставленные заказы из таблицы Orders. В результатах запроса высвечивать
--для колонки ShippedDate вместо значений NULL строку 'Not Shipped' – необходимо использовать CASЕ выражение. 
--Запрос должен высвечивать только колонки OrderID и ShippedDate.
select OrderId, 
case when ShippedDate is null then 'Not Shipped' end as ShippedDate
from Orders
where ShippedDate is null; 

--3. Выбрать в таблице Orders заказы, которые были доставлены после 5 мая 1998 года (ShippedDate) не включая эту дату
--или которые еще не доставлены. В запросе должны высвечиваться только колонки OrderID (переименовать в Order Number)
--и ShippedDate (переименовать в Shipped Date). В результатах запроса высвечивать для колонки ShippedDate 
--вместо значений NULL строку 'Not Shipped' - необходимо использовать функцию NVL, для остальных значений высвечивать
--дату в формате "ДД.ММ.ГГГГ".
select OrderId as "Order Number", nvl(TO_CHAR(ShippedDate, 'dd.mm.yyyy'), 'Not Shipped') as "Shipped Date"
from Orders
where ShippedDate > '5-05-1998' or ShippedDate is null;

---------------------------------------2. Использование операторов IN, DISTINCT, ORDER BY, NOT----------------------------------

--1. Выбрать из таблицы Customers всех заказчиков, проживающих в USA или Canada. Запрос сделать с только помощью оператора IN.
--Высвечивать колонки с именем пользователя и названием страны в результатах запроса. Упорядочить результаты запроса
--по имени заказчиков и по месту проживания.
select CompanyName, Country
from Customers
where Country in('USA', 'Canada')
order by Country, CompanyName;


--2. Выбрать из таблицы Customers всех заказчиков, не проживающих в USA и Canada. Запрос сделать с помощью оператора IN.
--Высвечивать колонки с именем пользователя и названием страны в результатах запроса. Упорядочить результаты запроса по имени заказчиков.
select CompanyName, Country
from Customers
where not Country in('USA', 'Canada')
order by CompanyName;

--3. Выбрать из таблицы Customers все страны, в которых проживают заказчики. Страна должна быть упомянута только один раз
--и список отсортирован по убыванию. Не использовать предложение GROUP BY. Высвечивать только одну колонку в результатах запроса.
select distinct(Country)
from Customers
order by Country desc;

-------------------------------------------3. Использование оператора BETWEEN, DISTINCT-----------------------------------------

--1. Выбрать все заказы (OrderID) из таблицы Order_Details (заказы не должны повторяться), где встречаются продукты
--с количеством от 3 до 10 включительно – это колонка Quantity в таблице Order_Details. Использовать оператор BETWEEN.
--Запрос должен высвечивать только колонку OrderID.
select distinct(OrderId)
from Order_Details
where Quantity between 3 and 10
order by OrderId;

--2. Выбрать всех заказчиков из таблицы Customers, у которых название страны начинается на буквы из диапазона B и G. 
--Использовать оператор BETWEEN. Проверить, что в результаты запроса попадает Germany. 
--Запрос должен высвечивать только колонки CustomerID и Country и отсортирован по Country.
select CustomerId, Country
from Customers
where Country between 'B' and 'H'
order by Country;

--3. Выбрать всех заказчиков из таблицы Customers, у которых название страны начинается на буквы из диапазона B и G,
--не используя оператор BETWEEN. Запрос должен высвечивать только колонки CustomerID и Country и отсортирован по Country.
--С помощью опции "Execute Explain Plan" определить какой запрос предпочтительнее 3.2 или 3.3, 
--необходимо объяснить почему и написать ответ в комментариях к текущему запросу.
select CustomerId, Country
from Customers
where Country >= 'B' and Country <= 'H'
order by Country;

-----------------------------------------------4. Использование оператора LIKE--------------------------------------------------

--1.В таблице Products найти все продукты (колонка ProductName), где встречается подстрока 'chocolade'. 
--Известно, что в подстроке 'chocolade' может быть изменена одна буква 'c' в середине - найти все продукты, 
--которые удовлетворяют этому условию. Подсказка: в результате должны быть найдены 2 строки.
select ProductName
from Products
where lower(ProductName) like '%cho_olade%';

---------------------------------------5. Использование агрегатных функций (SUM, COUNT)-----------------------------------------

--1. Найти общую сумму всех заказов из таблицы Order_Details с учетом количества закупленных товаров и скидок по ним.
--Результат округлить до сотых и отобразить в стиле:
--$X,XXX.XX где "$" - валюта доллары, "," – разделитель групп разрядов, "." – разделитель целой и дробной части,
--при этом дробная часть должна содержать цифры до сотых, пример: $1,234.00
--Скидка (колонка Discount) составляет процент из стоимости для данного товара. Для определения действительной цены на
--проданный продукт надо вычесть скидку из указанной в колонке UnitPrice цены. Результатом запроса должна быть одна запись
--с одной колонкой с названием колонки 'Totals'.
select TO_CHAR(sum((UnitPrice - UnitPrice * Discount) * Quantity), '$999,999,999.00') as Totals
from Order_Details;

--2. По таблице Orders найти количество заказов, которые еще не были доставлены (т.е. в колонке ShippedDate нет значения даты
--доставки). Использовать при этом запросе только оператор COUNT. Не использовать предложения WHERE и GROUP.
select count(*) - count(ShippedDate) as "Not Shipped Qty"
from Orders;

--3. По таблице Orders найти количество различных покупателей (CustomerID), сделавших заказы. Использовать функцию COUNT и не
--использовать предложения WHERE и GROUP.
select count(distinct(CustomerId)) as Qty
from Orders;


--------6. Явное соединение таблиц, самосоединения, использование агрегатных функций и предложений GROUP BY и HAVING------------

--1. По таблице Orders найти количество заказов с группировкой по годам. В результатах запроса надо высвечивать две колонки
--c названиями Year и Total. Написать проверочный запрос, который вычисляет количество всех заказов.
select count(*) as OrdersQty from Orders;

select extract(year from OrderDate) as Year, count(extract(year from OrderDate)) as Total
from Orders
group by extract(year from OrderDate);

--2. По таблице Orders найти количество заказов, cделанных каждым продавцом. Заказ для указанного продавца – это любая запись
--в таблице Orders, где в колонке EmployeeID задано значение для данного продавца. В результатах запроса надо высвечивать
--колонку с именем продавца (Должно высвечиваться имя полученное конкатенацией LastName & FirstName. 
--Эта строка LastName & FirstName должна быть получена отдельным запросом в колонке основного запроса. Также основной запрос 
--должен использовать группировку по EmployeeID.) с названием колонки 'Seller' и колонку c количеством заказов высвечивать с
--названием 'Amount'. Результаты запроса должны быть упорядочены по убыванию количества заказов.
select (select LastName || ' ' || FirstName from Employees e where e.EmployeeId = o.EmployeeId) as Seller, count(o.EmployeeId) as Amount
from Orders o
group by o.EmployeeId
order by Amount desc;

--3. Выбрать 5 стран в которых проживает наибольшее количество заказчиков. Список должен быть отсортирован по убыванию 
--популярности. Необходимо выводить два столбца - Country и Count (количество заказчиков).
select *
from (select Country, count(Country) as Count
        from Customers
        group by Country
        order by count(Country) desc)
where rownum <  6;

--4. По таблице Orders найти количество заказов, cделанных каждым продавцом и для каждого покупателя. Необходимо определить это
--только для заказов сделанных в 1998 году. В результатах запроса надо высвечивать колонку с именем продавца
--(название колонки 'Seller'), колонку с именем покупателя (название колонки 'Customer') и колонку c количеством заказов
--высвечивать с названием 'Amount'. В запросе необходимо использовать специальный оператор языка PL/SQL для работы 
--с выражением GROUP (Этот же оператор поможет выводить строку "ALL" в результатах запроса). 
--Подсказка: использовать операторы: ROLLUP, CUBE, GROUPING.
--Группировки должны быть сделаны по ID продавца и покупателя. Результаты запроса должны быть упорядочены по продавцу,
--покупателю и по убыванию количества продаж. В результатах должна быть сводная информация по продажам. 
select 
nvl2(to_char(EmployeeId), (select LastName || ' ' || FirstName from Employees e where o.EmployeeId = e.EmployeeId), 'ALL') as Seller,
nvl2(to_char(CustomerId), (select CompanyName from Customers c where o.CustomerId = c.CustomerId), 'ALL') as Customer, 
count(OrderId) as Amount
from Orders o
where extract(year from OrderDate) = '1998'
group by cube(EmployeeId, CustomerId)
order by Seller, Customer, Amount desc;

--5. Найти покупателей и продавцов, которые живут в одном городе. Если в городе живут только один или несколько продавцов
--или только один или несколько покупателей, то информация о таких покупателя и продавцах не должна попадать в результирующий
--набор. Не использовать конструкцию JOIN. В результатах запроса необходимо вывести следующие заголовки для результатов 
--запроса: 'Person', 'Type' (здесь надо выводить строку 'Customer' или 'Seller' в завимости от типа записи), 'City'. 
--Отсортировать результаты запроса по колонке 'City' и по 'Person'.
select CompanyName as Person, 'Customer' as Type, City
from Customers 
where City in (select City from Employees)
UNION
select LastName as Person, 'Seller' as Type, City
from Employees
where City in (select City from Customers)
order by City, Person;


--6. Найти всех покупателей, которые живут в одном городе. В запросе использовать соединение таблицы Customers 
--c собой - самосоединение. Высветить колонки CustomerID и City. Запрос не должен высвечивать дублируемые записи.
--Отсортировать результаты запроса по колонке City. Для проверки написать запрос, который высвечивает города, которые 
--встречаются более одного раза в таблице Customers. Это позволит проверить правильность запроса.
select distinct c1.CustomerId, c1.City
from Customers c1, Customers c2
where c1.City = c2.City and c1.CustomerId <> c2.CustomerId
order by c1.City;

select City 
from Customers
group by City
having count(City) > 1;

--7. По таблице Employees найти для каждого продавца его руководителя, т.е. кому он делает репорты. Высветить колонки с 
--именами 'User Name' (LastName) и 'Boss'. В колонках должны быть высвечены имена из колонки LastName. 
--Высвечены ли все продавцы в этом запросе?
select e1.LastName as "User Name", e2.LastName as Boss
from Employees e1, Employees e2
where e1.ReportsTo = e2.EmployeeId;

-----------------------------------------------------7. Использование Inner JOIN------------------------------------------------

--1.Определить продавцов, которые обслуживают регион 'Western' (таблица Region). Результаты запроса должны высвечивать два поля:
--'LastName' продавца и название обслуживаемой территории ('TerritoryDescription' из таблицы Territories). 
--Запрос должен использовать JOIN в предложении FROM. Для определения связей между таблицами Employees и Territories надо
--использовать графическую схему для базы Southwind.
select LastName, TerritoryDescription
from Employees e
    inner join EmployeeTerritories et on e.EmployeeId = et.EmployeeId
    inner join Territories t on et.TerritoryId = t.TerritoryId
    inner join Region r on t.RegionId = r.RegionId
where r.RegionDescription = 'Western';


--------------------------------------------------8. Использование Outer JOIN---------------------------------------------------

--1.Высветить в результатах запроса имена всех заказчиков из таблицы Customers и суммарное количество их заказов 
--из таблицы Orders. Принять во внимание, что у некоторых заказчиков нет заказов, но они также должны быть выведены 
--в результатах запроса. Упорядочить результаты запроса по возрастанию количества заказов.
select c.CompanyName, count(o.CustomerId)
from Customers c full join orders o on c.CustomerId = o.CustomerId
group by c.CompanyName
order by count(o.CustomerId);

--------------------------------------------------9. Использование подзапросов--------------------------------------------------

--1.Высветить всех поставщиков колонка CompanyName в таблице Suppliers, у которых нет хотя бы одного продукта 
--на складе (UnitsInStock в таблице Products равно 0). Использовать вложенный SELECT для этого запроса с использованием
--оператора IN. Можно ли использовать вместо оператора IN оператор '=' ?
select CompanyName
from Suppliers 
where SupplierId in (select SupplierId from Products where UnitsInStock = 0);

----------------------------------------------------10. Коррелированный запрос--------------------------------------------------

--1.Высветить всех продавцов, которые имеют более 150 заказов. Использовать вложенный коррелированный SELECT.
select LastName
from Employees
where EmployeeId in (select EmployeeId from Orders group by EmployeeId having count(EmployeeId) > 150);


------------------------------------------------------11. Использование EXISTS--------------------------------------------------

--1.Высветить всех заказчиков (таблица Customers), которые не имеют ни одного заказа (подзапрос по таблице Orders).
--Использовать коррелированный SELECT и оператор EXISTS.
select CustomerId 
from Customers c
where not exists (select c.CustomerId from Orders o where c.CustomerId = o.CustomerId);

-----------------------------------------------------12. Использование строковых функций----------------------------------------

--1.Для формирования алфавитного указателя Employees высветить из таблицы Employees список только тех букв алфавита,
--с которых начинаются фамилии Employees (колонка LastName ) из этой таблицы. Алфавитный список должен быть отсортирован
--по возрастанию.
select distinct substr(LastName, 1, 1) as Letter
from Employees
order by Letter;