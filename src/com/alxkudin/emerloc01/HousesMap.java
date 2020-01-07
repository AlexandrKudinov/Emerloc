package com.alxkudin.emerloc01;

import java.util.*;

import static com.alxkudin.emerloc01.MapStructure.*;


public class HousesMap {
    Map<Node, House> houses = new HashMap<>();

/*
    public House getHouse(Node node) {
        for (House house : houses.values()) {
            if (house.contain(node)) return house;
        }
        throw new NoSuchElementException();
    }
 */


    public static void buildHouseBlocks() {
        INSTANCE.get(0, 0).  setType(NodeType.HOUSE_BLOCK);  // set corner nodes type
        INSTANCE.get(0, HEIGHT - 1).setType(NodeType.HOUSE_BLOCK);
        INSTANCE.get(WIDTH - 1, 0).setType(NodeType.HOUSE_BLOCK);
        INSTANCE.get(WIDTH - 1, HEIGHT - 1).setType(NodeType.HOUSE_BLOCK);
        INSTANCE.get(1, 0).setRandomAsHouseBlock();
        INSTANCE.get(1, HEIGHT - 1).setEqualType(INSTANCE.get(1, 0));
        INSTANCE.get(0, 1).setRandomAsHouseBlock();
        INSTANCE.get(WIDTH - 1, 1).setEqualType(INSTANCE.get(0, 1));

        for (int i = 2; i < WIDTH - 1; i++) { // set first & last line
            if (!INSTANCE.get(i, 0).leftNodeTypeIs(NodeType.HOUSE_BLOCK)) {
                INSTANCE.get(i, 0).setType(NodeType.HOUSE_BLOCK);
                INSTANCE.get(i, HEIGHT - 1).setEqualType(INSTANCE.get(i, 0));
                continue;
            }
            if (INSTANCE.get(i, 0).twoLeftNodesTypesAre(NodeType.HOUSE_BLOCK)) {
                continue;
            }
            if (INSTANCE.get(i, 0).twoRightNodesTypesAre(NodeType.HOUSE_BLOCK)) {
                continue;
            }
            INSTANCE.get(i, 0).setRandomAsHouseBlock();
            INSTANCE.get(i, HEIGHT - 1).setEqualType(INSTANCE.get(i, 0));
        }

        for (int i = 2; i < HEIGHT - 1; i++) { //set first and last column
            if (!INSTANCE.get(0, i).upNodeTypeIs(NodeType.HOUSE_BLOCK)) {
                INSTANCE.get(0, i).setType(NodeType.HOUSE_BLOCK);
                INSTANCE.get(WIDTH - 1, i).setEqualType(INSTANCE.get(0, i));
                continue;
            }
            if (INSTANCE.get(0, i).twoUpNodesTypesAre(NodeType.HOUSE_BLOCK)) {
                continue;
            }
            if (INSTANCE.get(0, i).twoDownNodesTypesAre(NodeType.HOUSE_BLOCK)) {
                continue;
            }
            INSTANCE.get(0, i).setRandomAsHouseBlock();
            INSTANCE.get(WIDTH - 1, i).setEqualType(INSTANCE.get(0, i));
        }

        for (int i = 1; i < WIDTH - 1; i++) { //set second line
            if (INSTANCE.get(i, 1).leftDownNodeTypeIs(NodeType.HOUSE_BLOCK) ||
                    (!INSTANCE.get(i, 0).thisAndLeftNodesTypesAre(NodeType.HOUSE_BLOCK)) ||
                    (INSTANCE.get(i - 1, 1).isHouseBlock() &&
                            INSTANCE.get(i, 0).thisAndLeftNodesTypesAre(NodeType.HOUSE_BLOCK))) {
                continue;
            }
            INSTANCE.get(i, 1).setRandomAsHouseBlock();
        }

        for (int i = 1; i < WIDTH - 1; i++) { //set before last line
            if ((INSTANCE.get(i, HEIGHT - 1).getLeftNode().isHouseBlock()) ||
                    (INSTANCE.get(i, HEIGHT - 2).getRightNode().getUpNode().isHouseBlock())) {
                continue;
            }
            INSTANCE.get(i, HEIGHT - 2).setRandomAsHouseBlock();
        }

        for (int i = 2; i < HEIGHT - 2; i++) { //set second column
            if ((INSTANCE.get(1, i).columnNodeAreaContainHouseBlocks() ||
                    !INSTANCE.get(1, i).twoLeftNodesTypesAre(NodeType.HOUSE_BLOCK))) {
                continue;
            }
            INSTANCE.get(1, i).setRandomAsHouseBlock();
        }

        for (int i = 2; i < HEIGHT - 2; i++) {  //set before last column
            if ((INSTANCE.get(WIDTH - 2, i).columnNodeAreaContainHouseBlocks() ||
                    (!INSTANCE.get(WIDTH - 2, i).getRightNode().thisAndUpNodesTypeIs(NodeType.HOUSE_BLOCK)))) {
                continue;
            }
            INSTANCE.get(WIDTH - 2, i).setRandomAsHouseBlock();
        }

        for (int j = 2; j < HEIGHT - 2; j++) {  //set the main square
            for (int i = 2; i < WIDTH - 2; i++) {
                Node node = INSTANCE.get(i, j);
                if (node.areaIsEmpty()) {
                    node.setType(NodeType.HOUSE_BLOCK);
                    continue;
                }
                if (node.areaContainHouseBlocks()) {
                    continue;
                }
                node.setRandomAsHouseBlock();
            }
        }

        for (int j = 0; j < HEIGHT; j++) {   //fill spaces surrounded not with HOUSE_BLOCKS
            for (int i = 0; i < WIDTH; i++) {
                Node node = INSTANCE.get(i, j);
                if (!node.typeIs(NodeType.HOUSE_BLOCK)) {
                    if (node.houseBlockCanBeAdded()) {
                        node.setType(NodeType.HOUSE_BLOCK);
                    }
                    if (node.partOfAreaIsEmpty()) {
                        node.setType(NodeType.HOUSE_BLOCK);
                    }
                }
            }
        }
    }


    public void unionHouses() {
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                if (INSTANCE.get(i, j).typeIs(NodeType.HOUSE_BLOCK)) {
                    House house = new House();
                    house.add(INSTANCE.get(i, j));
                    houses.put(INSTANCE.get(i, j), house);
                    System.out.println(house.toString());
                }
            }
            System.out.println("new string");
        }
        System.out.println(houses.size());

        System.out.println();

    }
/*
    public void addWaterIntake() {
        List<List<House>> unionHouses = new LinkedList<>();
        Random random = new Random();
        boolean depend;
        List<House> sortedHouses = new ArrayList<>(houses.values());
        sortedHouses.sort((o1, o2) -> {
            if (o1.getHouseFragments().size() > o2.getHouseFragments().size()) {
                return 1;
            } else if (o1.getHouseFragments().size() == o2.getHouseFragments().size()) {
                return 0;
            } else return -1;
        });


        for (House house : sortedHouses) {
            System.out.println(house.toString());
        }


        for (int i = 0; i < sortedHouses.size(); i++) {
            depend=false;
            House house = sortedHouses.get(i);
            List<Fragment> houseFragments = house.getHouseFragments();
            one:
            {

                for (int j = 0; j < houseFragments.size(); j++) {
                    Fragment fragment = map.get(houseFragments.get(j).getX(), houseFragments.get(j).getY());
                    if ((fragment.getX() != 0 && fragment.leftTypeIs(10)) ||
                            (fragment.getY() != 0 && fragment.upTypeIs(10)) ||
                            (fragment.getY() != (MapStructure.HEIGHT - 1) && fragment.downTypeIs(10)) ||
                            (fragment.getX() != (MapStructure.WIDTH - 1) && fragment.rightTypeIs(10))) {
                        depend=true;
                    }
                }




                Map<Integer, Fragment> waterIntake = new HashMap<>();
                int count = 0;
                for (int j = 0; j < houseFragments.size(); j++) {
                    Fragment fragment = map.get(houseFragments.get(j).getX(), houseFragments.get(j).getY());
                    //  System.out.println(fragment);


                    if (fragment.getX() != 0 && fragment.leftTypeIs(14)) {
                        Fragment fragment1 = new Fragment(fragment.getLeft().getX(), fragment.getY());
                        fragment1.setType(40);
                        waterIntake.put(count++, fragment1);
                        if (fragment.getDoubleLeft().getX() != 0 && fragment.doubleLeftTypeIs(6) && !depend) {
                            Fragment fragment2 = new Fragment(fragment.getLeft().getX(), fragment.getY());
                            fragment2.setType(10);
                            waterIntake.put(count++, fragment2);
                        }
                    }

                    if (fragment.getY() != 0 && fragment.upTypeIs(16)) {
                        Fragment fragment1 = new Fragment(fragment.getX(), fragment.getUp().getY());
                        fragment1.setType(42);
                        waterIntake.put(count++, fragment1);
                        if (fragment.getDoubleUp().getY() != 0 && fragment.doubleUpTypeIs(6) && !depend) {
                            Fragment fragment2 = new Fragment(fragment.getX(), fragment.getUp().getY());
                            fragment2.setType(10);
                            waterIntake.put(count++, fragment2);
                        }
                    }

                    if (fragment.getY() != (HEIGHT - 1) && fragment.downTypeIs(16)) {
                        Fragment fragment1 = new Fragment(fragment.getX(), fragment.getDown().getY());
                        fragment1.setType(46);
                        waterIntake.put(count++, fragment1);
                        if (fragment.getDoubleDown().getY() != (HEIGHT - 1) && fragment.doubleDownTypeIs(6) && !depend) {
                            Fragment fragment2 = new Fragment(fragment.getX(), fragment.getDown().getY());
                            fragment2.setType(10);
                            waterIntake.put(count++, fragment2);
                        }
                    }

                    if (fragment.getX() != (MapStructure.WIDTH - 1) && fragment.rightTypeIs(14)) {
                        Fragment fragment1 = new Fragment(fragment.getRight().getX(), fragment.getY());
                        fragment1.setType(44);
                        waterIntake.put(count++, fragment1);
                        if (fragment.getDoubleRight().getX() != (WIDTH - 1) && fragment.doubleRightTypeIs(6) && !depend) {
                            Fragment fragment2 = new Fragment(fragment.getRight().getX(), fragment.getY());
                            fragment2.setType(10);
                            waterIntake.put(count++, fragment2);
                        }
                    }






                }

                if (waterIntake.size() == 0) {
                    for (int j = 0; j < houseFragments.size(); j++) {
                        Fragment fragment = map.get(houseFragments.get(j).getX(), houseFragments.get(j).getY());

                        if (fragment.getX() != 0 && fragment.leftTypeIs(26)) {
                            Fragment fragment1 = new Fragment(fragment.getLeft().getX(), fragment.getY());
                            fragment1.setType(56);
                            waterIntake.put(count++, fragment1);

                        }

                        if (fragment.getY() != 0 && fragment.upTypeIs(28)) {
                            Fragment fragment1 = new Fragment(fragment.getX(), fragment.getUp().getY());
                            fragment1.setType(58);
                            waterIntake.put(count++, fragment1);

                        }

                        if (fragment.getY() != (HEIGHT - 1) && fragment.downTypeIs(30)) {
                            Fragment fragment1 = new Fragment(fragment.getX(), fragment.getDown().getY());
                            fragment1.setType(50);
                            waterIntake.put(count++, fragment1);

                        }

                        if (fragment.getX() != (MapStructure.WIDTH - 1) && fragment.rightTypeIs(32)) {
                            Fragment fragment1 = new Fragment(fragment.getRight().getX(), fragment.getY());
                            fragment1.setType(52);
                            waterIntake.put(count++, fragment1);

                        }

                    }

                }
                    System.out.println(count);
                    System.out.println(waterIntake.size());
                    //  System.out.println((int) (Math.random()*count));
                    int rnd = (int) (Math.random() * count);

                    if(waterIntake.get(rnd).getType()==10){

                    }


                    System.out.println(rnd);




                    //  System.out.println(map.get(waterIntake.get(rnd).getX(),waterIntake.get(rnd).getY()));
                    map.get(waterIntake.get(rnd).getX(), waterIntake.get(rnd).getY()).setType(waterIntake.get(rnd).getType());
                }

            }

        }

 */

}
