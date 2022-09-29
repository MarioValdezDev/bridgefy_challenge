package mx.mariovaldez.code_challenge.domain.mapper

interface Mapper<Input, Output> {

    fun map(value: Input): Output

    fun map(values: List<Input>): List<Output> = values.map { map(it) }
}
