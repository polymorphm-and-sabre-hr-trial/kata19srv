README
======

Kata19
------

The objective of this kata is to write a program that accepts start and end
words and, using words from the dictionary, builds a word chain between them.
For added programming fun, return the shortest word chain that solves each
puzzle. For example, you can turn “lead” into “gold” in four steps (lead, load,
goad, gold), and “ruby” into “code” in six steps (ruby, rubs, robs, rods, rode,
code).

Default Profile
---------------

Compiling with default profile makes Web Application (WAR):

.. code-block::

    mvn clean package

Using after deploying:

.. code-block::

    time curl http://127.0.0.1:8080/kata19-srv-1.0-SNAPSHOT/findpath/ruby/code/
    ["ruby","rubs","cubs","cobs","cods","code"]
    real	0m0.020s
    user	0m0.011s
    sys	0m0.004s

.. code-block::

    $ time curl http://127.0.0.1:8080/kata19-srv-1.0-SNAPSHOT/findpath/bsd/qnx/
    {"code":"PATHFINDING_FAILURE","message":"the path to these words is not found"}
    real	0m0.017s
    user	0m0.010s
    sys	0m0.003s

Cli Profile
-----------

Compiling with ``cli`` profile makes simplified command line application:

.. code-block::

    mvn clean package -Pcli

Run examples:

.. code-block::

    $ time java -jar target/kata19-srv-1.0-SNAPSHOT.jar cat box ruby code code done kill live bean luna linux unix bsd qnx
    indexing word list...
    finding pathes...
    * cat -> bat -> bar -> bor -> box
    * ruby -> rube -> robe -> rode -> code
    * code -> cone -> done
    * kill -> fill -> file -> five -> live
    * bean -> Bean -> Benn -> Bena -> Buna -> luna
    * linux {path not found} unix
    * bsd {path not found} qnx

    real	0m2.524s
    user	0m6.065s
    sys	0m0.237s

.. code-block::

    $ time java -jar target/kata19-srv-1.0-SNAPSHOT.jar '/home/regular-user/Desktop/wordlist.utf-8.txt' cat box ruby code code done kill live bean luna linux unix bsd qnx
    indexing word list...
    finding pathes...
    * cat -> bat -> bot -> box
    * ruby -> rube -> robe -> rode -> code
    * code -> cone -> done
    * kill -> fill -> file -> five -> live
    * bean -> Bean -> Benn -> Bena -> Buna -> luna
    * linux {path not found} unix
    * bsd {path not found} qnx

    real	0m2.380s
    user	0m5.753s
    sys	0m0.221s

