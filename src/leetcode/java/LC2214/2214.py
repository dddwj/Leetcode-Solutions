class Solution:
    def minimumHealth(self, damage: List[int], armor: int) -> int:
        health = 1
        maxDam = -math.inf

        for dam in damage:
            health += dam
            maxDam = max(maxDam, dam)

        health -= min(maxDam, armor)
        return health

    """
    health - dam1 - dam2 - dam3 + min(armor, maxDam) >> 0
    """

    def minimumHealth_concise(self, damage: List[int], armor: int) -> int:
        return sum(damage) + 1 - min(max(damage), armor)