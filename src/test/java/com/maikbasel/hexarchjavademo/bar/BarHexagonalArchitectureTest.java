package com.maikbasel.hexarchjavademo.bar;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(
        packages = {
                "com.maikbasel.hexarchjavademo.common",
                "com.maikbasel.hexarchjavademo.config",
                "com.maikbasel.hexarchjavademo.bar",
        },
        importOptions = {
                ImportOption.DoNotIncludeTests.class,
                ImportOption.DoNotIncludeJars.class
        }
)
// All ArchRule are annotated with @SuppressWarnings("unused") as these are in fact not unused, but rather tested
// against by the ArchUnit Framework as long as they are marked with the @ArchTest annotation.
// TODO Make reusable for other modules.
class BarHexagonalArchitectureTest {

    private static final String INPUT_ADAPTER = "Input Adapter";
    private static final String OUTPUT_ADAPTER = "Output Adapter";
    private static final String DRIVEN_PORTS = "Driven Ports";
    private static final String DRIVING_PORTS = "Driving Ports";
    private static final String APPLICATION_SERVICES = "Application Services";
    private static final String DOMAIN = "Domain";
    private static final String CONFIG = "Config";
    private static final String COMMON = "Common";

    @ArchTest
    @SuppressWarnings("unused")
    static final ArchRule config = layeredArchitecture()
            .consideringOnlyDependenciesInAnyPackage("com.maikbasel.hexarchjavademo")
            .layer(CONFIG).definedBy("..config..")
            .whereLayer(CONFIG).mayNotBeAccessedByAnyLayer()
            .because("In the Config Layer we will make use of the Spring Framework " +
                    "and apply the Inversion of Control (IoC) pattern to register any Framework-specific configuration beans " +
                    "or pass configuration properties down the application. " +
                    "As this is the upper most layer of the application " +
                    "and we want all our dependencies to point inwards this Layer should never be accessed by any other Layer.");

    @ArchTest
    @SuppressWarnings("unused")
    static final ArchRule common = layeredArchitecture()
            .consideringOnlyDependenciesInAnyPackage("com.maikbasel.hexarchjavademo")
            .layer(COMMON).definedBy("..common..")
            .whereLayer(COMMON).mayNotAccessAnyLayer()
            .because("Like Config this Common Layer finds itself at the edge of our application. This Layer " +
                    "contains Code shared by other Layers of the application. Which means that it should not depend on such other Layers " +
                    "in order to prevent cyclic dependencies.");

    @ArchTest
    @SuppressWarnings("unused")
    static final ArchRule adapters = layeredArchitecture()
            .consideringOnlyDependenciesInAnyPackage("com.maikbasel.hexarchjavademo")
            .optionalLayer(INPUT_ADAPTER).definedBy("..adapter.in..")
            .layer(OUTPUT_ADAPTER).definedBy("..adapter.out..")
            .layer(CONFIG).definedBy("..config..")
            .whereLayer(INPUT_ADAPTER).mayOnlyBeAccessedByLayers(CONFIG)
            .because("For Adapters we can apply the Inversion of Control (IoC) pattern and leave initialization " +
                    "of these classes largely to the Spring Framework. If any further configuration is required, we can " +
                    "do so in the Config Layer.");

    @ArchTest
    @SuppressWarnings("unused")
    static final ArchRule adapter = classes()
            .that().resideInAnyPackage("..adapter..")
            .should().bePackagePrivate()
            .orShould().bePrivate()
            .because("Adapter classes and their DTOs should be initialized and executed by the Spring Framework. " +
                    "There is no reason to make them publicly available.");

    @ArchTest
    @SuppressWarnings("unused")
    static final ArchRule ports= layeredArchitecture()
            .consideringOnlyDependenciesInAnyPackage("com.maikbasel.hexarchjavademo")
            .optionalLayer(INPUT_ADAPTER).definedBy("..adapter.in..")
            .layer(OUTPUT_ADAPTER).definedBy("..adapter.out..")
            .layer(DRIVING_PORTS).definedBy("..application.port.driving..")
            .layer(DRIVEN_PORTS).definedBy("..application.port.driven..")
            .layer(CONFIG).definedBy("..config..")
            .whereLayer(DRIVING_PORTS).mayOnlyBeAccessedByLayers(INPUT_ADAPTER, CONFIG)
            .whereLayer(DRIVEN_PORTS).mayOnlyBeAccessedByLayers(OUTPUT_ADAPTER, CONFIG)
            .because("The Adapter Layer should only ever interact with the Application Layer via it's Ports. " +
                    "While the Application Services will implement driven Ports aka " +
                    "Use-Cases, these should not be called directly, but via their specific Port interfaces.");

    @ArchTest
    @SuppressWarnings("unused")
    static final ArchRule port = classes()
            .that().resideInAnyPackage("..application.port..")
            .should().bePublic()
            .because("The whole point of the Port interfaces and their DTOs declared here is to be publicly available, " +
                    "as they are the only way from and to the application's core logic.");

    @ArchTest
    @SuppressWarnings("unused")
    static final ArchRule applicationServices = layeredArchitecture()
            .consideringOnlyDependenciesInAnyPackage("com.maikbasel.hexarchjavademo")
            .layer(DOMAIN).definedBy("..domain..")
            .layer(APPLICATION_SERVICES).definedBy("..application.service..")
            .whereLayer(APPLICATION_SERVICES).mayNotBeAccessedByAnyLayer()
            .whereLayer(APPLICATION_SERVICES).mayOnlyAccessLayers(DOMAIN)
            .because("Application Services should only be concerned with orchestrating the flow of data in the " +
                    "application either from or to the Domain Entities. They will also direct those entities to use " +
                    "their Critical Business Rules to achieve the goals of the Use-Case implemented by the Service. " +
                    "If this requires them to communicate with external systems they should do so via a Driven Port.");

    @ArchTest
    @SuppressWarnings("unused")
    static final ArchRule applicationService = classes()
            .that().resideInAnyPackage("..application.service..")
            .should().bePackagePrivate()
            .orShould().bePrivate()
            .because("The application services should only be called via the public Use-Case interface they implement.");

    @ArchTest
    @SuppressWarnings("unused")
    static final ArchRule domainEntities = layeredArchitecture()
            .consideringOnlyDependenciesInLayers()
            .layer(DOMAIN).definedBy("..domain..")
            .whereLayer(DOMAIN).mayNotAccessAnyLayer()
            .because("The Domain Layer will hold the Domain Entities of the application, " +
                    "which are the core of the domain. They encapsulate Enterprise-wide business rules and attributes. " +
                    "Our goal here is to keep the application core free of any dependencies in order to make sure, " +
                    "that changes in other layers don’t affect it. This way the Domain can evolve free of dependencies.");
}
