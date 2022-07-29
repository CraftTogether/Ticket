package commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

fun String.asComponent(): Component = PlainTextComponentSerializer.plainText().deserialize(this)
fun Component.asString(): String = PlainTextComponentSerializer.plainText().serialize(this)