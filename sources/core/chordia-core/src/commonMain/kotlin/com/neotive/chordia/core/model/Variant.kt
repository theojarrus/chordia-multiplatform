package com.neotive.chordia.core.model

data class Variant(
    val points: List<Point>,
    val rows: List<Row>
) {

    val notes get() = points.map(Point::note)
    val lines get() = points.map(Point::line)
}
