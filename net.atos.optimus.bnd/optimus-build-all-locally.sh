#!/bin/sh
echo "***************************************"
echo "* Optimus Global Maven Builder Script *"
echo "***************************************"
mvn -f ../net.atos.optimus.parent/pom.xml install; 
mvn -f ../net.atos.optimus.common.parent/pom.xml install;
mvn -f ../net.atos.optimus.emf.metamodels.parent/pom.xml install;
mvn -f ../net.atos.optimus.t2m.java.parent/pom.xml install;
mvn -f ../net.atos.optimus.m2m.engine.parent/pom.xml install;
mvn -f ../net.atos.optimus.m2m.javaxmi.parent/pom.xml install;
mvn -f ../net.atos.optimus.m2t.selector.parent/pom.xml install;
mvn -f ../net.atos.optimus.m2t.merger.java.parent/pom.xml install;
mvn -f ../net.atos.optimus.m2t.java.parent/pom.xml install;
mvn -f ../net.atos.optimus.m2m.engine.sdk.parent/pom.xml install;
