package com.shalan.searchgithub.base.mapper

interface Mapper<Entity, Model> {

    fun mapFromEntity(entity: Entity): Model

    fun mapFromModel(model: Model): Entity

}