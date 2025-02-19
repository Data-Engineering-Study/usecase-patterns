rootProject.name = "usecase-patterns"

/** main module */
listOf(
    ":multiple-storage-location:flink",
    ":multiple-storage-location:beam",
).forEach(::include)

listOf(
    ":common:flink",
    ":common:beam",
).forEach(::include)
