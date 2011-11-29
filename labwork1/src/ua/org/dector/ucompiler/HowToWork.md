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
    select by name ~ "hello"

delete
------
    delete by id = 1

update
------
    update by id = 1: name = "new hello", value = 4
    update by id = 1: name = "new hello"
    update by id = 1: value = 4

insert
------
    insert 18: "hello2" -> 0.5                          ;insert one record
    insert [19: "hello3" -> 0.6, 20: "hello4" -> 0.7]   ;multi-insert