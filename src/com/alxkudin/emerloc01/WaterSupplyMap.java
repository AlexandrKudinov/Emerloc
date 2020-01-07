package com.alxkudin.emerloc01;

import java.util.Random;

import static com.alxkudin.emerloc01.ArmatureType.*;
import static com.alxkudin.emerloc01.MapStructure.*;
import static com.alxkudin.emerloc01.NodeType.HOUSE;
import static com.alxkudin.emerloc01.NodeType.PIPELINE;

public class WaterSupplyMap {

    public static void build() {
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {

                if (INSTANCE.get(i, j).isPipelineBlock()) {
                    Node node = INSTANCE.get(i, j);
                    Pipe pipe1 = new Pipe();
                    INSTANCE.get(i, j).setPipe(pipe1);

                    if (!node.upNodeTypeIs(HOUSE) &&     //cross
                            !node.leftNodeTypeIs(HOUSE) &&
                            !node.rightNodeTypeIs(HOUSE) &&
                            !node.downNodeTypeIs(HOUSE)) {
                        Pipe pipe = new Pipe();
                        pipe.addParts(LEFT, UP, RIGHT, DOWN);
                        INSTANCE.get(i, j).setPipe(pipe);
                        continue;
                    }

                    if (!node.leftNodeTypeIs(HOUSE) &&  //up valve
                            !node.upNodeTypeIs(HOUSE) &&
                            !node.rightNodeTypeIs(HOUSE) &&
                            (node.downNodeTypeIs(HOUSE))) {
                        Pipe pipe = new Pipe();
                        pipe.addParts(UP, LEFT, RIGHT);
                        pipe.setValve(new Valve(UP));
                        INSTANCE.get(i, j).setPipe(pipe);
                        continue;
                    }


                    if ((!node.rightNodeTypeIs(HOUSE) && !node.leftNodeTypeIs(HOUSE)) && //horizontal
                            ((node.upNodeTypeIs(HOUSE) && (node.downNodeTypeIs(HOUSE) ||
                                    ((!node.rightDownNodeTypeIs(HOUSE)) || !node.leftDownNodeTypeIs(HOUSE)))) ||
                                    (node.downNodeTypeIs(HOUSE) && (!node.rightUpNodeTypeIs(HOUSE) ||
                                            !node.leftUpNodeTypeIs(HOUSE))))) {
                        Pipe pipe = new Pipe();
                        pipe.addParts(LEFT, RIGHT);
                        INSTANCE.get(i, j).setPipe(pipe);
                        continue;
                    }


                    if (!node.downNodeTypeIs(HOUSE) && !node.rightNodeTypeIs(HOUSE) && // down valve
                            !node.leftNodeTypeIs(HOUSE) &&
                            (node.upNodeTypeIs(HOUSE))) {
                        Pipe pipe = new Pipe();
                        pipe.addParts(DOWN, LEFT, RIGHT);
                        pipe.setValve(new Valve(DOWN));
                        INSTANCE.get(i, j).setPipe(pipe);
                        continue;
                    }


                    if (!node.leftNodeTypeIs(HOUSE) && !node.downNodeTypeIs(HOUSE) && //left down corner
                            node.leftDownNodeTypeIs(HOUSE) && node.rightNodeTypeIs(HOUSE) &&
                            (node.upNodeTypeIs(HOUSE) || !node.leftUpNodeTypeIs(HOUSE))) {
                        Pipe pipe = new Pipe();
                        pipe.addParts(LEFT, DOWN);
                        INSTANCE.get(i, j).setPipe(pipe);
                        continue;
                    }


                    if (!node.leftNodeTypeIs(HOUSE) && node.leftUpNodeTypeIs(HOUSE) &&  //left up corner
                            !node.upNodeTypeIs(HOUSE) && node.rightNodeTypeIs(HOUSE) && node.downNodeTypeIs(HOUSE)) {
                        Pipe pipe = new Pipe();
                        pipe.addParts(LEFT, UP);
                        INSTANCE.get(i, j).setPipe(pipe);
                        continue;
                    }


                    if (node.leftNodeTypeIs(HOUSE) && node.downNodeTypeIs(HOUSE) && // right up corner
                            node.rightUpNodeTypeIs(HOUSE) &&
                            !node.upNodeTypeIs(HOUSE) && !node.rightNodeTypeIs(HOUSE)) {
                        Pipe pipe = new Pipe();
                        pipe.addParts(RIGHT, UP);
                        INSTANCE.get(i, j).setPipe(pipe);
                        continue;
                    }

                    if (node.leftNodeTypeIs(HOUSE) && !node.downNodeTypeIs(HOUSE) && //right down corner
                            !node.rightNodeTypeIs(HOUSE) && node.rightDownNodeTypeIs(HOUSE) &&
                            (node.upNodeTypeIs(HOUSE) || !node.rightUpNodeTypeIs(HOUSE))) {
                        Pipe pipe = new Pipe();
                        pipe.addParts(RIGHT, DOWN);
                        INSTANCE.get(i, j).setPipe(pipe);
                        continue;
                    }


                    if (node.rightNodeTypeIs(HOUSE) && !node.upNodeTypeIs(HOUSE) && // left valve
                            !node.downNodeTypeIs(HOUSE) && !node.leftNodeTypeIs(HOUSE) &&
                            (node.leftUpNodeTypeIs(HOUSE) || node.leftDownNodeTypeIs(HOUSE))) {
                        Pipe pipe = new Pipe();
                        pipe.addParts(DOWN, LEFT, UP);
                        pipe.setValve(new Valve(LEFT));
                        INSTANCE.get(i, j).setPipe(pipe);
                        continue;
                    }


                    if (node.leftNodeTypeIs(HOUSE)&& !node.rightNodeTypeIs(HOUSE)&& //right valve
                    !node.upNodeTypeIs(HOUSE) && !node.downNodeTypeIs(HOUSE) &&
                            (node.rightUpNodeTypeIs(HOUSE) || node.rightDownNodeTypeIs(HOUSE))) {
                        Pipe pipe = new Pipe();
                        pipe.addParts(DOWN, RIGHT, UP);
                        pipe.setValve(new Valve(RIGHT));
                        INSTANCE.get(i, j).setPipe(pipe);
                        continue;
                    }

                    Pipe pipe = new Pipe(); //vertical
                    pipe.addParts(UP, DOWN);
                    INSTANCE.get(i, j).setPipe(pipe);
                }
            }
        }


        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                System.out.print(INSTANCE.get(i, j).getType());
            }
            System.out.println();
        }


    }


}
