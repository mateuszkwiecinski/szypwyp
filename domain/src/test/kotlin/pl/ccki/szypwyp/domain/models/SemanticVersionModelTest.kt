package pl.ccki.szypwyp.domain.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SemanticVersionModelTest {

    @Test
    fun compareTo() {
        val ver200 = SemanticVersionModel(2, 0, 0, null)
        val ver201 = SemanticVersionModel(2, 0, 1, null)
        val ver210 = SemanticVersionModel(2, 1, 0, null)
        val ver211 = SemanticVersionModel(2, 1, 1, null)
        val ver2010 = SemanticVersionModel(2, 0, 10, null)
        val ver2100 = SemanticVersionModel(2, 10, 0, null)
        val ver200beta1 = SemanticVersionModel(2, 0, 0, 1)
        val ver200beta2 = SemanticVersionModel(2, 0, 0, 2)

        val data = listOf(ver200, ver201, ver200beta1, ver200beta2, ver210,
            ver211, ver2010, ver2100)

        val result = data.sortedBy { it.compareTo(it) }

        assertThat(result).containsExactlyInAnyOrder(ver200beta1, ver200beta2, ver200, ver201,
            ver2010, ver211, ver210, ver2100)
    }
}
