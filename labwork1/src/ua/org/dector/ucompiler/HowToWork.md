Table structure
===============

+--------+----------+----------+
|   id   |   name   |   value  |
| (long) | (string) | (double) |
+--------+----------+----------+


Query samples
=============

select
------
    select by id = 1            ;this is comment
    select by name = "hello"
    select by value = 2.0

    select by name = "hello", value = 1.5   ;multiselect

delete
------
    delete by id = 1

update
------
    update by id = 1: name = "new hello"
    update by name = "hello", value = 2.0: name = "newest hello"

insert
------
    insert 18: "hello2" -> 0.5                          ;insert one record
    insert [19: "hello3" -> 0.6, 20: "hello4" -> 0.7]   ;multi-insert
    insert "hello5" -> 0.8                              ;no-id insert (will be last+1 => 21)
    insert [22: "hello6" -> 0.9, "hello7" -> 1.0]       ;with-id and no-id may be combined

Dev. info
=========

Filters field and name compatibility
------------------------------------

id      (long)      ->  __long__
name    (string)    ->  long, double, __string__
value   (double)    ->  long, __double__


xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx