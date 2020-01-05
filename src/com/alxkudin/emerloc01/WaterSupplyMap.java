package com.alxkudin.emerloc01;

import static com.alxkudin.emerloc01.MapStructure.HEIGHT;
import static com.alxkudin.emerloc01.MapStructure.WIDTH;

public class WaterSupplyMap {
    /*
    public static void build() {
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {

                if (GameField.housesMap.map.get(i, j).getType() == 0) {
                    Fragment fragment = GameField.housesMap.map.get(i, j);

                    if (!fragment.upTypeIs(6) && !fragment.doubleUpTypeIs(6) &&     //cross 10
                            !fragment.leftTypeIs(6) && !fragment.doubleLeftTypeIs(6) &&
                            !fragment.rightTypeIs(6) && !fragment.doubleRightTypeIs(6) &&
                            !fragment.downTypeIs(6) && !fragment.doubleDownTypeIs(6)) {
                        fragment.setType(10);
                        continue;
                    }

                    if (!fragment.leftTypeIs(6) &&  //up valve 28
                            !fragment.upTypeIs(6) &&
                            !fragment.doubleUpTypeIs(6) &&
                            !fragment.rightTypeIs(6) &&
                            (fragment.downTypeIs(6))) {
                        fragment.setType(28);
                        continue;
                    }


                    if (!fragment.leftTypeIs(6) &&   //horizontal 16
                            !fragment.rightTypeIs(6) &&
                            ((!fragment.downTypeIs(6) && !fragment.rightDownTypeIs(6) && fragment.upTypeIs(6)) ||
                                    (fragment.upTypeIs(6) && fragment.downTypeIs(6)) || (fragment.downTypeIs(6) && !fragment.upTypeIs(6) && (!fragment.rightUpTypeIs(6) || !fragment.leftUpTypeIs(6))) ||
                                    (!fragment.downTypeIs(6) && !fragment.leftDownTypeIs(6) && fragment.upTypeIs(6))
                            )) {
                        fragment.setType(16);
                        continue;
                    }


                    if (!fragment.leftTypeIs(6) &&   //down valve  32
                            !fragment.rightTypeIs(6) &&
                            !fragment.downTypeIs(6) && !fragment.doubleDownTypeIs(6) &&
                            (fragment.upTypeIs(6) || (!fragment.upTypeIs(6) && (!fragment.rightUpTypeIs(6) || !fragment.leftUpTypeIs(6))))) {
                        fragment.setType(32);
                        continue;
                    }


                    if (!fragment.leftTypeIs(6) &&  // left down corner 18
                            !fragment.downTypeIs(6) &&
                            fragment.leftDownTypeIs(6) &&
                            ((fragment.rightTypeIs(6) && fragment.upTypeIs(6) && fragment.rightUpTypeIs(6)) || (((!fragment.rightUpTypeIs(6) || !fragment.getLeft().getDoubleUp().typeIs(6) || !fragment.getRight().getDoubleUp().typeIs(6)) && !fragment.upTypeIs(6) && (!fragment.leftUpTypeIs(6)))))) {
                        fragment.setType(18);
                        continue;


                    }
                    if (!fragment.leftTypeIs(6) &&  //  left up corner 20
                            !fragment.upTypeIs(6) &&
                            fragment.leftUpTypeIs(6) &&
                            fragment.rightTypeIs(6) &&
                            fragment.downTypeIs(6) &&
                            fragment.rightDownTypeIs(6)) {
                        fragment.setType(20);
                        continue;
                    }


                    if (!fragment.upTypeIs(0) &&   //right up corner 22
                            !fragment.rightTypeIs(6) &&
                            fragment.rightUpTypeIs(6) &&
                            fragment.leftTypeIs(6) &&
                            fragment.leftDownTypeIs(6) &&
                            fragment.downTypeIs(6)) {
                        fragment.setType(22);
                        continue;
                    }

                    if (!fragment.rightTypeIs(6) && //right down corner 24
                            !fragment.downTypeIs(6) &&
                            fragment.rightDownTypeIs(6) &&
                            fragment.leftTypeIs(6) &&
                            ((fragment.upTypeIs(6) &&
                                    fragment.leftUpTypeIs(6)) || (!fragment.upTypeIs(6) && !fragment.rightUpTypeIs(6)))) {
                        fragment.setType(24);
                        continue;
                    }


                    if (!fragment.leftTypeIs(6) &&
                            !fragment.doubleLeftTypeIs(6) &&// left valve 26
                            !fragment.upTypeIs(6) &&
                            !fragment.downTypeIs(6) &&
                            fragment.rightTypeIs(6) &&
                            (fragment.leftUpTypeIs(6) ||
                                    fragment.leftDownTypeIs(6))) {
                        fragment.setType(26);
                        continue;
                    }


                    if (!fragment.upTypeIs(6) &&   //right valve  30
                            !fragment.rightTypeIs(6) &&
                            !fragment.doubleRightTypeIs(6) &&
                            !fragment.downTypeIs(6) &&
                            fragment.leftTypeIs(6)) {
                        fragment.setType(30);
                        continue;
                    }

                    if (!fragment.upTypeIs(6) &&   //vertical 14
                            !fragment.downTypeIs(6) &&
                            ((!fragment.leftTypeIs(6) && !fragment.rightTypeIs(6)) || (fragment.rightTypeIs(6) ||
                                    fragment.leftTypeIs(6)))) {
                        fragment.setType(14);
                        continue;
                    }
                }
            }
        }

        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                System.out.print(GameField.housesMap.map.get(i, j).getType() + " ");
            }
            System.out.println();

        }
    }

     */
}
