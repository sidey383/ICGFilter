package ru.nsu.group21208.interaction;

public interface InterpolationModInteractions {

    Interaction bilinearInteraction();

    Interaction bicubicInteraction();

    Interaction nearestNeighborInteraction();

}
