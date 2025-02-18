rootProject.name = "usecase-patterns"

listOf(
    ":multiple-storage-location:flink",
    ":multiple-storage-location:beam",
).forEach(::include)
