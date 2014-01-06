package de.proglabor.aufgabe4.modell;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Random;
import java.util.TreeMap;

import de.proglabor.aufgabe4.console.RuntimeConfiguration;
import de.proglabor.aufgabe4.controller.Controller;
import de.proglabor.aufgabe4.utils.Helper;
import de.proglabor.aufgabe4.report.ReportBewegung;
import de.proglabor.aufgabe4.report.ReportPflanze;
import de.proglabor.aufgabe4.report.ReportTiere;
import de.proglabor.aufgabe4.utils.Logger;

/**
 * @author sirmonkey
 *
 */

/**
 * @author id261708
 */
public class World extends Observable {

    private static final int MAXIMUM_WORLD_X = 40;
    private static final int MAXIMUM_WORLD_Y = 30;
    private static final int JUNGLE_WIDTH = 10;
    private static final int JUNGLE_HEIGHT = 10;
    private static final int PLANT_ENERGY = 80;
    private static final int INITIAL_ENERGY = 1000;
    private static final int REPRODUCTION_ENERGY = 200;

    private boolean empty;
    private int width = 0;
    private int height = 0;
    private TreeMap<Plant, Integer> plantContainer;
    private int plantEnergy = 0;

    private int widthJungle = 0;
    private int heightJungle = 0;
    private int jungleLimitX1 = 0;
    private int jungleLimitX2 = 0;
    private int jungleLimitY1 = 0;
    private int jungleLimitY2 = 0;

    private LinkedList<Animal> animalContainer;
    private int initialEnergy = 0;
    private int reproductionEnergy = 0;

    private int maxPlants = 0;
    private int maxAnimals = 0;
    private boolean reportPlants = false;
    private boolean reportTiere = false;
    private boolean reportBewegung = false;
    private boolean reportReproduce = false;

    private int days;

    /**
     * z�hlt die geborenen Tiere
     */
    private int bornCount = 0;
    /**
     * z�hlt die gestorbenen Tiere
     */
    private int deadCount = 0;

    /**
     * z�hlt die gepflanzte Pflanzen
     */
    private int planted = 0;
    /**
     * z�hlt die gegessen Pflanzen
     */
    private int eaten = 0;

    /**
     * Something
     */
    public World() {
        this(MAXIMUM_WORLD_X, MAXIMUM_WORLD_Y, JUNGLE_WIDTH, JUNGLE_HEIGHT,
                PLANT_ENERGY, INITIAL_ENERGY, REPRODUCTION_ENERGY);
    }

    /**
     * @param width
     * @param height
     * @param widthJungle
     * @param heightJungle
     * @param plantEnergy
     * @param initialEnergy
     * @param reproductionEnergy
     */
    public World(int width, int height, int widthJungle, int heightJungle,
                 int plantEnergy, int initialEnergy, int reproductionEnergy) {
        initAll(width, height, widthJungle, heightJungle, plantEnergy,
                initialEnergy, reproductionEnergy);
    }

    /**
     * Initializer
     *
     * @param heightWorld H�he der World
     * @param widthWorld  Breite der World
     * @param widthJ      Breite des Jungels
     * @param heightJ     H�he des Jungels
     * @param pEnergy     How much Energy Plants provide
     * @param iEnergy     of the Animals
     * @param rEnergy     Reproduction Threshold
     */
    public void initAll(int widthWorld, int heightWorld, int widthJ,
                        int heightJ, int pEnergy, int iEnergy,
                        int rEnergy) {
        if (widthWorld <= 0) {
            throw new IllegalArgumentException("Width must be >= 1");
        } else {
            this.width = widthWorld;
            if (heightWorld <= 0) {
                throw new IllegalArgumentException("Height must be >= 1");
            } else {
                this.height = heightWorld;
                if (widthJ <= 0) {
                    throw new IllegalArgumentException(
                            "Jungle Width must be >= 1");
                } else {
                    if (widthJ > widthWorld) {
                        throw new IllegalArgumentException(
                                "Jungle Width must be <= Width");
                    } else {
                        this.widthJungle = widthJ;
                        if (heightJ < 0) {
                            throw new IllegalArgumentException(
                                    "Jungle Height must be > 0");
                        } else {
                            if (heightJ > heightWorld) {
                                throw new IllegalArgumentException(
                                        "Jungle Height must be <= Height");
                            } else {
                                this.heightJungle = heightJ;
                                initJungleLimits();
                                if (pEnergy < 0) {
                                    throw new IllegalArgumentException(
                                            "PlantEnergy must be >= 0");
                                } else {
                                    this.plantEnergy = pEnergy;
                                    initPlantContainer();
                                    if (iEnergy < 1) {
                                        throw new IllegalArgumentException(
                                                "InitialEnergy must be >= 1");
                                    } else {
                                        this.initialEnergy = iEnergy;
                                        if (rEnergy < 1) {
                                            throw new IllegalArgumentException(
                                                    "InitialEnergy must be >= 1");
                                        } else {

                                            this.reproductionEnergy = rEnergy;
                                            initAnimalContainer();
                                            this.empty = false;
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
    }

    /**
     * initialisiert den Pflanzen
     */
    public void initPlantContainer() {
        planted = 0;
        eaten = 0;
        this.plantContainer = new TreeMap<Plant, Integer>();
    }

    /**
     * initialisiert den Tiercontainer
     */
    public void initAnimalContainer() {
        bornCount = 0;
        deadCount = 0;
        this.animalContainer = new LinkedList<Animal>();

    }

    /**
     * Welcome to the Jungle we have funny Games.
     */
    private void initJungleLimits() {
        jungleLimitX1 = (width - widthJungle) / 2;
        jungleLimitX2 = (width + widthJungle) / 2;
        jungleLimitY1 = (height - heightJungle) / 2;
        jungleLimitY2 = (height + heightJungle) / 2;
    }

    // World Actions ADD

    /**
     * Add a Plant at the Coordinates
     *
     * @param x Koordinate
     * @param y Koordinate
     */
    public void addPlant(int x, int y) {
        Plant key = new Plant(x, y);
        addPlant(key);
    }

    /**
     * f�gt eine Plant hinzu
     *
     * @param plant the Plant to be added
     */
    public void addPlant(Plant plant) {
        planted++;
        Plant key = plant;
        Integer value = plantContainer.get(key);
        if (plantContainer.get(key) == null) {
            value = 0;
        }
        value++;
        plantContainer.put(key, value);
    }

    // World Actions REMOVE

    /**
     * Add an Animal
     *
     * @param animal Animal
     */
    public void addAnimal(Animal animal) {
        bornCount++;
        animalContainer.add(animal);
    }

    /**
     * Removes a Plant at the Given X,Y Coordinates.
     *
     * @param x Coordinate
     * @param y Coordinate
     */
    public void removePlant(int x, int y) {
        int count = totalPlantsAt(x, y);
        Plant key = new Plant(x, y);
        if (count == 1) {
            plantContainer.remove(key);
            eaten++;
        } else if (count > 1) {
            count--;
            plantContainer.put(key, count);
            eaten++;
        }
    }

    /**
     * Kills an Animal
     *
     * @param animal to be killed
     */
    public void removeAnimal(Animal animal) {
        animalContainer.remove(animal);
        deadCount++;
    }

    // Random ADD Actions

    /**
     * Add a Plant at a Random Location inside the World
     */
    public void randomAddPlant() {
        Random rand = new Random();
        int x = Helper.randInt(0, width - 1, rand);
        int y = Helper.randInt(0, height - 1, rand);
        addPlant(x, y);

    }

    /**
     * f�gt eine Plant an eine beliebige Stelle in den Jungel
     */
    public void randomAddPlantJungle() {
        Random rand = new Random();
        int x = Helper.randInt(jungleLimitX1, jungleLimitX2 - 1, rand);
        int y = Helper.randInt(jungleLimitY1, jungleLimitY2 - 1, rand);
        addPlant(x, y);

    }

    /**
     * f�gt ein tier an eine beliebige Stelle hinzu
     */
    public void randomAddAnimal() {
        Random rand = new Random();
        int x = Helper.randInt(0, width - 1, rand);
        int y = Helper.randInt(0, height - 1, rand);
        Animal animal = new Animal(x, y, initialEnergy);
        addAnimal(animal);

    }

    // Animal Actions -- Getting Extracted to an Animal Controller maybe

    /**
     * bewegt ein Animal in der World
     *
     * @param milka ein tier
     */
    public void moveAnimal(Animal milka) {

        int whereIsTheCow = animalContainer.indexOf(milka);
        animalContainer.get(whereIsTheCow).move(height, width);

    }

    /**
     * f�hrt alle Aktionen eines Tiers aus
     *
     * @param animal Animal
     */
    public void animalAction(Animal animal) {
        // eat
        if (totalPlantsAt(animal.getX(), animal.getY()) >= 1) {
            animal.eat(plantEnergy);
            removePlant(animal.getX(), animal.getY());
        }
        animal.turn();
        // reproduce
        if (animal.getEnergy() >= reproductionEnergy) {
            Animal baby = animal.reproduce();
            addAnimal(baby);
        }
        // Move
        animal.energyDecay(1);
        if (animal.getEnergy() == 0) {
            removeAnimal(animal);
        } else {
            moveAnimal(animal);
        }

    }

    /**
     * A Day in the Simulation
     */
    public void day() {
        this.randomAddPlant();
        this.randomAddPlantJungle();
        @SuppressWarnings("unchecked")
        LinkedList<Animal> tmp = (LinkedList<Animal>) animalContainer.clone();
        for (Animal animal : tmp) {
            this.animalAction(animal);
        }
        setChanged();
        notifyObservers();

    }

    public void runSim() {
        Logger.info("Simulation Started");
        // Monkey-Patch
        Animal weronika = new Animal(getWidth() / 2, getHeight() / 2, getInitialEnergy());
        this.addAnimal(weronika);
        setChanged();
        notifyObservers();
        ReportTiere reportT = null;
        ReportPflanze reportPflanze = null;
        if (this.reportTiere) {
            reportT = new ReportTiere(days);
            reportT.reportHeader();
        }

        if (this.reportPlants) {
            reportPflanze = new ReportPflanze(days);
            reportPflanze.reportHeader();
        }
        for (int i = 0; i < days; i++) {
            day();
            if (this.reportTiere) {
                int animalsWorld = totalAnimals();
                int animalsJungle = totalAnimalsJungle();
                Hashtable<String, Integer> reportTiereContent = new Hashtable<String, Integer>();
                reportTiereContent.put("Tag", i + 1);
                reportTiereContent.put("World", animalsWorld);
                reportTiereContent.put("Dschungel", animalsJungle);
                reportT.reportContent(reportTiereContent);
                maxAnimals = Math.max(maxAnimals, animalsWorld);
            }
            if (this.reportPlants) {
                int plantsWorld = totalPlants();
                int plantsJungle = totalPlantsJungle();
                Hashtable<String, Integer> reportPflanzeContent = new Hashtable<String, Integer>();
                reportPflanzeContent.put("Tag", i + 1);
                reportPflanzeContent.put("World", plantsWorld);
                reportPflanzeContent.put("Dschungel", plantsJungle);
                reportPflanze.reportContent(reportPflanzeContent);
                maxPlants = Math.max(maxPlants, plantsWorld);
            }
            if (bornCount == deadCount) {
                Logger.info("Simulation Stoped All Animals are Dead");
                break;
//				throw new Exception("Aborting Simulation after: " + i + " day(s) -->" + "all Animals are dead");
            }

        }
        Logger.info("Simulation Done");
        if (this.reportTiere) {
            reportT.reportFooter(maxAnimals);
            reportT.reportWriter(reportT.reportOutput, "ReportTiere.csv");
        }
        if (this.reportPlants) {
            reportPflanze.reportFooter(maxPlants);
            reportPflanze.reportWriter(reportPflanze.reportOutput, "ReportPflanze.csv");
        }
        if (this.reportBewegung) {
            ReportBewegung report = new ReportBewegung(days);
            report.reportHeader();
            report.reportContent(animalContainer);
            report.reportFooter(animalContainer);
            report.reportWriter(report.reportOutput, "ReportBewegung.csv");
        }
    }

    /**
     * @return the plantContainer
     */
    public TreeMap<Plant, Integer> getContainerPlants() {
        return plantContainer;
    }

    /**
     * @return the animalContainer
     */

    public LinkedList<Animal> getContainerAnimals() {
        return animalContainer;
    }

    /**
     * @param plantEnergy the plantEnergy to set
     */
    public void setPlantEnergy(int plantEnergy) {
        this.plantEnergy = plantEnergy;
    }

    /**
     * @param reproductionEnergy the reproductionEnergy to set
     */
    public void setReproductionEnergy(int reproductionEnergy) {
        this.reproductionEnergy = reproductionEnergy;
    }

    /**
     * @return The Total Plant Count
     */
    public int totalPlants() {

        return planted - eaten;
    }

    /**
     * @return Total Plants in the Jungle
     */
    public int totalPlantsJungle() {
        int total = 0;
        for (Map.Entry<Plant, Integer> entry : plantContainer.entrySet()) {
            int cellX = entry.getKey().getX();
            int cellY = entry.getKey().getY();
            if (cellX >= jungleLimitX1 && cellX <= jungleLimitX2 && cellY >= jungleLimitY1 && cellY <= jungleLimitY2) {
                Integer value = entry.getValue();
                total += value;
            }
        }
        return total;
    }

    /**
     * @param x Coordinate
     * @param y Coordinate
     * @return Plant count at a Given Coordinate
     */
    public int totalPlantsAt(int x, int y) {
        Plant key = new Plant(x, y);
        Integer value = plantContainer.get(key);
        if (plantContainer.get(key) == null) {
            return 0;
        } else {
            return value;
        }
    }

    /**
     * Gesamt Tieranzahl
     *
     * @return gesamt anzahl der Tiere
     */
    public int totalAnimals() {
        return animalContainer.size();
    }

    /**
     * @return total Animals in the Jungle
     */
    public int totalAnimalsJungle() {
        int counter = 0;
        for (Animal animal : animalContainer) {
            if (animal.getX() >= jungleLimitX1 && animal.getX() <= jungleLimitX2
                    && animal.getY() >= jungleLimitY1
                    && animal.getY() <= jungleLimitY2) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * z�hlt die Tiere an einer bestimmten Stelle
     *
     * @param x x-Koordinate
     * @param y y-Koordinate
     * @return Anzahl der Tiere
     */
    public int totalAnimalsAt(int x, int y) {
        int counter = 0;
        for (Animal animal : animalContainer) {
            if (animal.getX() == x && animal.getY() == y) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * @return the empty
     */
    public boolean isEmpty() {
        return empty;
    }

    /**
     * @param empty the empty to set
     */
    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    /**
     * Breite
     *
     * @return breite der World
     */
    public int getWidth() {
        return width;
    }

    /**
     * H�he
     *
     * @return h�he der World
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the initialEnergy
     */
    public int getInitialEnergy() {
        return initialEnergy;
    }

    /**
     * @return the reproductionEnergy
     */
    public int getReproductionEnergy() {
        return reproductionEnergy;
    }

    /**
     * @return the planted
     */
    public int getPlantedCount() {
        return planted;
    }

    /**
     * @return the bornCount
     */
    public int getBornCount() {
        return bornCount;
    }

    /**
     * @return the eaten Plants
     */
    public int getEatenCount() {
        return eaten;
    }

    /**
     * @return the deadCount
     */
    public int getDeadCount() {
        return deadCount;
    }

    /**
     * @return the jungleLimitX1
     */
    public int getJungleLimitX1() {
        return jungleLimitX1;
    }

    /**
     * @return the jungleLimitX2
     */
    public int getJungleLimitX2() {
        return jungleLimitX2;
    }

    /**
     * @return the jungleLimitY1
     */
    public int getJungleLimitY1() {
        return jungleLimitY1;
    }

    /**
     * @return the jungleLimitY2
     */
    public int getJungleLimitY2() {
        return jungleLimitY2;
    }

    /**
     * @param reportPlants the reportPlants to set
     */
    public void setReportPlants(boolean reportPlants) {
        this.reportPlants = reportPlants;
    }

    /**
     * @param reportTiere the reportTiere to set
     */
    public void setReportAnimal(boolean reportTiere) {
        this.reportTiere = reportTiere;
    }

    /**
     * @param reportBewegung the reportBewegung to set
     */
    public void setReportMovement(boolean reportBewegung) {
        this.reportBewegung = reportBewegung;
    }

    /**
     * @param reportReproduce the reportReproduce to set
     */
    public void setReportReproduce(boolean reportReproduce) {
        this.reportReproduce = reportReproduce;
    }

    public void setRuntimeConfiguration(RuntimeConfiguration runtimeConfiguration) {
        if (runtimeConfiguration.createAnimalReport()) {
            setReportAnimal(true);
        }
        if (runtimeConfiguration.createPlantReport()) {
            setReportPlants(true);
        }
        if (runtimeConfiguration.createMovementReport()) {
            setReportMovement(true);
        }

        if(runtimeConfiguration.getSimulationCount() != -1){
            days = runtimeConfiguration.getSimulationCount();
        }
    }

}
