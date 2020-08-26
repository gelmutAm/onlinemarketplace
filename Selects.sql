--1. ������ � ������ ������ Date, NULL ����������, ����������� ������. ����������� ������������ �������� � ����������� �������
--� ����������� �� ���������� �������������� �������� ���������� �������. �������� � ����������� ������� ������ ������������ �������.

--1. ������� � ������� Orders ������, ������� ���� ���������� ����� 5 ��� 1998 ���� (������� ShippedDate) ������������
--� ������� ���������� � ShipVia >= 2. ������ �������� ���� ������ ���� ������ ��� ����� ������������ ����������.
--���� ����� ������������ ����� ��� ���� �������. ������ ������ ����������� ������ ������� OrderID, ShippedDate � ShipVia.
--�������� ������ ���� �� ������ ������ � NULL-�� � ������� ShippedDate.
select OrderID, ShippedDate, ShipVia
from Orders
where ShippedDate >= '5-05-1998' and ShipVia >= 2;

--2. �������� ������, ������� ������� ������ �������������� ������ �� ������� Orders. � ����������� ������� �����������
--��� ������� ShippedDate ������ �������� NULL ������ 'Not Shipped' � ���������� ������������ CAS� ���������. 
--������ ������ ����������� ������ ������� OrderID � ShippedDate.
select OrderId, 
case when ShippedDate is null then 'Not Shipped' end as ShippedDate
from Orders
where ShippedDate is null; 

--3. ������� � ������� Orders ������, ������� ���� ���������� ����� 5 ��� 1998 ���� (ShippedDate) �� ������� ��� ����
--��� ������� ��� �� ����������. � ������� ������ ������������� ������ ������� OrderID (������������� � Order Number)
--� ShippedDate (������������� � Shipped Date). � ����������� ������� ����������� ��� ������� ShippedDate 
--������ �������� NULL ������ 'Not Shipped' - ���������� ������������ ������� NVL, ��� ��������� �������� �����������
--���� � ������� "��.��.����".
select OrderId as "Order Number", nvl(TO_CHAR(ShippedDate, 'dd.mm.yyyy'), 'Not Shipped') as "Shipped Date"
from Orders
where ShippedDate > '5-05-1998' or ShippedDate is null;

---------------------------------------2. ������������� ���������� IN, DISTINCT, ORDER BY, NOT----------------------------------

--1. ������� �� ������� Customers ���� ����������, ����������� � USA ��� Canada. ������ ������� � ������ ������� ��������� IN.
--����������� ������� � ������ ������������ � ��������� ������ � ����������� �������. ����������� ���������� �������
--�� ����� ���������� � �� ����� ����������.
select CompanyName, Country
from Customers
where Country in('USA', 'Canada')
order by Country, CompanyName;


--2. ������� �� ������� Customers ���� ����������, �� ����������� � USA � Canada. ������ ������� � ������� ��������� IN.
--����������� ������� � ������ ������������ � ��������� ������ � ����������� �������. ����������� ���������� ������� �� ����� ����������.
select CompanyName, Country
from Customers
where not Country in('USA', 'Canada')
order by CompanyName;

--3. ������� �� ������� Customers ��� ������, � ������� ��������� ���������. ������ ������ ���� ��������� ������ ���� ���
--� ������ ������������ �� ��������. �� ������������ ����������� GROUP BY. ����������� ������ ���� ������� � ����������� �������.
select distinct(Country)
from Customers
order by Country desc;

-------------------------------------------3. ������������� ��������� BETWEEN, DISTINCT-----------------------------------------

--1. ������� ��� ������ (OrderID) �� ������� Order_Details (������ �� ������ �����������), ��� ����������� ��������
--� ����������� �� 3 �� 10 ������������ � ��� ������� Quantity � ������� Order_Details. ������������ �������� BETWEEN.
--������ ������ ����������� ������ ������� OrderID.
select distinct(OrderId)
from Order_Details
where Quantity between 3 and 10
order by OrderId;

--2. ������� ���� ���������� �� ������� Customers, � ������� �������� ������ ���������� �� ����� �� ��������� B � G. 
--������������ �������� BETWEEN. ���������, ��� � ���������� ������� �������� Germany. 
--������ ������ ����������� ������ ������� CustomerID � Country � ������������ �� Country.
select CustomerId, Country
from Customers
where Country between 'B' and 'H'
order by Country;

--3. ������� ���� ���������� �� ������� Customers, � ������� �������� ������ ���������� �� ����� �� ��������� B � G,
--�� ��������� �������� BETWEEN. ������ ������ ����������� ������ ������� CustomerID � Country � ������������ �� Country.
--� ������� ����� "Execute Explain Plan" ���������� ����� ������ ���������������� 3.2 ��� 3.3, 
--���������� ��������� ������ � �������� ����� � ������������ � �������� �������.
select CustomerId, Country
from Customers
where Country >= 'B' and Country <= 'H'
order by Country;

-----------------------------------------------4. ������������� ��������� LIKE--------------------------------------------------

--1.� ������� Products ����� ��� �������� (������� ProductName), ��� ����������� ��������� 'chocolade'. 
--��������, ��� � ��������� 'chocolade' ����� ���� �������� ���� ����� 'c' � �������� - ����� ��� ��������, 
--������� ������������� ����� �������. ���������: � ���������� ������ ���� ������� 2 ������.
select ProductName
from Products
where lower(ProductName) like '%cho_olade%';

---------------------------------------5. ������������� ���������� ������� (SUM, COUNT)-----------------------------------------

--1. ����� ����� ����� ���� ������� �� ������� Order_Details � ������ ���������� ����������� ������� � ������ �� ���.
--��������� ��������� �� ����� � ���������� � �����:
--$X,XXX.XX ��� "$" - ������ �������, "," � ����������� ����� ��������, "." � ����������� ����� � ������� �����,
--��� ���� ������� ����� ������ ��������� ����� �� �����, ������: $1,234.00
--������ (������� Discount) ���������� ������� �� ��������� ��� ������� ������. ��� ����������� �������������� ���� ��
--��������� ������� ���� ������� ������ �� ��������� � ������� UnitPrice ����. ����������� ������� ������ ���� ���� ������
--� ����� �������� � ��������� ������� 'Totals'.
select TO_CHAR(sum((UnitPrice - UnitPrice * Discount) * Quantity), '$999,999,999.00') as Totals
from Order_Details;

--2. �� ������� Orders ����� ���������� �������, ������� ��� �� ���� ���������� (�.�. � ������� ShippedDate ��� �������� ����
--��������). ������������ ��� ���� ������� ������ �������� COUNT. �� ������������ ����������� WHERE � GROUP.
select count(*) - count(ShippedDate) as "Not Shipped Qty"
from Orders;

--3. �� ������� Orders ����� ���������� ��������� ����������� (CustomerID), ��������� ������. ������������ ������� COUNT � ��
--������������ ����������� WHERE � GROUP.
select count(distinct(CustomerId)) as Qty
from Orders;


--------6. ����� ���������� ������, ��������������, ������������� ���������� ������� � ����������� GROUP BY � HAVING------------

--1. �� ������� Orders ����� ���������� ������� � ������������ �� �����. � ����������� ������� ���� ����������� ��� �������
--c ���������� Year � Total. �������� ����������� ������, ������� ��������� ���������� ���� �������.
select count(*) as OrdersQty from Orders;

select extract(year from OrderDate) as Year, count(extract(year from OrderDate)) as Total
from Orders
group by extract(year from OrderDate);

--2. �� ������� Orders ����� ���������� �������, c�������� ������ ���������. ����� ��� ���������� �������� � ��� ����� ������
--� ������� Orders, ��� � ������� EmployeeID ������ �������� ��� ������� ��������. � ����������� ������� ���� �����������
--������� � ������ �������� (������ ������������� ��� ���������� ������������� LastName & FirstName. 
--��� ������ LastName & FirstName ������ ���� �������� ��������� �������� � ������� ��������� �������. ����� �������� ������ 
--������ ������������ ����������� �� EmployeeID.) � ��������� ������� 'Seller' � ������� c ����������� ������� ����������� �
--��������� 'Amount'. ���������� ������� ������ ���� ����������� �� �������� ���������� �������.
select (select LastName || ' ' || FirstName from Employees e where e.EmployeeId = o.EmployeeId) as Seller, count(o.EmployeeId) as Amount
from Orders o
group by o.EmployeeId
order by Amount desc;

--3. ������� 5 ����� � ������� ��������� ���������� ���������� ����������. ������ ������ ���� ������������ �� �������� 
--������������. ���������� �������� ��� ������� - Country � Count (���������� ����������).
select *
from (select Country, count(Country) as Count
        from Customers
        group by Country
        order by count(Country) desc)
where rownum <  6;

--4. �� ������� Orders ����� ���������� �������, c�������� ������ ��������� � ��� ������� ����������. ���������� ���������� ���
--������ ��� ������� ��������� � 1998 ����. � ����������� ������� ���� ����������� ������� � ������ ��������
--(�������� ������� 'Seller'), ������� � ������ ���������� (�������� ������� 'Customer') � ������� c ����������� �������
--����������� � ��������� 'Amount'. � ������� ���������� ������������ ����������� �������� ����� PL/SQL ��� ������ 
--� ���������� GROUP (���� �� �������� ������� �������� ������ "ALL" � ����������� �������). 
--���������: ������������ ���������: ROLLUP, CUBE, GROUPING.
--����������� ������ ���� ������� �� ID �������� � ����������. ���������� ������� ������ ���� ����������� �� ��������,
--���������� � �� �������� ���������� ������. � ����������� ������ ���� ������� ���������� �� ��������. 
select 
nvl2(to_char(EmployeeId), (select LastName || ' ' || FirstName from Employees e where o.EmployeeId = e.EmployeeId), 'ALL') as Seller,
nvl2(to_char(CustomerId), (select CompanyName from Customers c where o.CustomerId = c.CustomerId), 'ALL') as Customer, 
count(OrderId) as Amount
from Orders o
where extract(year from OrderDate) = '1998'
group by cube(EmployeeId, CustomerId)
order by Seller, Customer, Amount desc;

--5. ����� ����������� � ���������, ������� ����� � ����� ������. ���� � ������ ����� ������ ���� ��� ��������� ���������
--��� ������ ���� ��� ��������� �����������, �� ���������� � ����� ���������� � ��������� �� ������ �������� � ��������������
--�����. �� ������������ ����������� JOIN. � ����������� ������� ���������� ������� ��������� ��������� ��� ����������� 
--�������: 'Person', 'Type' (����� ���� �������� ������ 'Customer' ��� 'Seller' � ��������� �� ���� ������), 'City'. 
--������������� ���������� ������� �� ������� 'City' � �� 'Person'.
select CompanyName as Person, 'Customer' as Type, City
from Customers 
where City in (select City from Employees)
UNION
select LastName as Person, 'Seller' as Type, City
from Employees
where City in (select City from Customers)
order by City, Person;


--6. ����� ���� �����������, ������� ����� � ����� ������. � ������� ������������ ���������� ������� Customers 
--c ����� - ��������������. ��������� ������� CustomerID � City. ������ �� ������ ����������� ����������� ������.
--������������� ���������� ������� �� ������� City. ��� �������� �������� ������, ������� ����������� ������, ������� 
--����������� ����� ������ ���� � ������� Customers. ��� �������� ��������� ������������ �������.
select distinct c1.CustomerId, c1.City
from Customers c1, Customers c2
where c1.City = c2.City and c1.CustomerId <> c2.CustomerId
order by c1.City;

select City 
from Customers
group by City
having count(City) > 1;

--7. �� ������� Employees ����� ��� ������� �������� ��� ������������, �.�. ���� �� ������ �������. ��������� ������� � 
--������� 'User Name' (LastName) � 'Boss'. � �������� ������ ���� ��������� ����� �� ������� LastName. 
--��������� �� ��� �������� � ���� �������?
select e1.LastName as "User Name", e2.LastName as Boss
from Employees e1, Employees e2
where e1.ReportsTo = e2.EmployeeId;

-----------------------------------------------------7. ������������� Inner JOIN------------------------------------------------

--1.���������� ���������, ������� ����������� ������ 'Western' (������� Region). ���������� ������� ������ ����������� ��� ����:
--'LastName' �������� � �������� ������������� ���������� ('TerritoryDescription' �� ������� Territories). 
--������ ������ ������������ JOIN � ����������� FROM. ��� ����������� ������ ����� ��������� Employees � Territories ����
--������������ ����������� ����� ��� ���� Southwind.
select LastName, TerritoryDescription
from Employees e
    inner join EmployeeTerritories et on e.EmployeeId = et.EmployeeId
    inner join Territories t on et.TerritoryId = t.TerritoryId
    inner join Region r on t.RegionId = r.RegionId
where r.RegionDescription = 'Western';


--------------------------------------------------8. ������������� Outer JOIN---------------------------------------------------

--1.��������� � ����������� ������� ����� ���� ���������� �� ������� Customers � ��������� ���������� �� ������� 
--�� ������� Orders. ������� �� ��������, ��� � ��������� ���������� ��� �������, �� ��� ����� ������ ���� �������� 
--� ����������� �������. ����������� ���������� ������� �� ����������� ���������� �������.
select c.CompanyName, count(o.CustomerId)
from Customers c full join orders o on c.CustomerId = o.CustomerId
group by c.CompanyName
order by count(o.CustomerId);

--------------------------------------------------9. ������������� �����������--------------------------------------------------

--1.��������� ���� ����������� ������� CompanyName � ������� Suppliers, � ������� ��� ���� �� ������ �������� 
--�� ������ (UnitsInStock � ������� Products ����� 0). ������������ ��������� SELECT ��� ����� ������� � ��������������
--��������� IN. ����� �� ������������ ������ ��������� IN �������� '=' ?
select CompanyName
from Suppliers 
where SupplierId in (select SupplierId from Products where UnitsInStock = 0);

----------------------------------------------------10. ��������������� ������--------------------------------------------------

--1.��������� ���� ���������, ������� ����� ����� 150 �������. ������������ ��������� ��������������� SELECT.
select LastName
from Employees
where EmployeeId in (select EmployeeId from Orders group by EmployeeId having count(EmployeeId) > 150);


------------------------------------------------------11. ������������� EXISTS--------------------------------------------------

--1.��������� ���� ���������� (������� Customers), ������� �� ����� �� ������ ������ (��������� �� ������� Orders).
--������������ ��������������� SELECT � �������� EXISTS.
select CustomerId 
from Customers c
where not exists (select c.CustomerId from Orders o where c.CustomerId = o.CustomerId);

-----------------------------------------------------12. ������������� ��������� �������----------------------------------------

--1.��� ������������ ����������� ��������� Employees ��������� �� ������� Employees ������ ������ ��� ���� ��������,
--� ������� ���������� ������� Employees (������� LastName ) �� ���� �������. ���������� ������ ������ ���� ������������
--�� �����������.
select distinct substr(LastName, 1, 1) as Letter
from Employees
order by Letter;