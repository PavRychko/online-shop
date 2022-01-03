package dao.mapper;

import entity.Product;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductRowMapperTest {

    @Test
    public void mapRowTest() throws Exception {
        ProductRowMapper productRowMapper = new ProductRowMapper();
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("tomato");
        when(mockResultSet.getInt("price")).thenReturn(10);

        LocalDateTime dateTime = LocalDateTime.of(2021, Month.APRIL, 12, 10, 22, 14);
        Timestamp timestampForMock = Timestamp.valueOf(dateTime);
        when(mockResultSet.getTimestamp("creationDate")).thenReturn(timestampForMock);

        Product actual = productRowMapper.mapRow(mockResultSet);

        assertNotNull(actual);
        assertEquals(1, actual.getId());
        assertEquals("tomato", actual.getName());
        assertEquals(10, actual.getPrice());
        assertEquals(timestampForMock, actual.getCreationDate());
    }
}
