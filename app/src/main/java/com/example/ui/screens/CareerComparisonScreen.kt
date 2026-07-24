package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.CareerPathModel
import com.example.viewmodel.MainViewModel

@Composable
fun CareerComparisonScreen(viewModel: MainViewModel) {
    val careerPaths = viewModel.getCareerPaths()
    var selectedFilter by remember { mutableStateOf("All") } // "All", "Top 3", "Recommended"

    val filteredList = when (selectedFilter) {
        "Top 3" -> careerPaths.filter { it.isTop3 }
        "Recommended" -> careerPaths.filter { it.isRecommendedPrimary }
        else -> careerPaths
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(bottom = 32.dp, top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Summary Header Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("career_analysis_header_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Analytics,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Tech Path Analysis for B.Sc. CS in India",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Evaluated across 10 technical domains based on fresher hiring volume in India, degree flexibility, salary growth, DSA requirements, and AI automation resilience.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )
                }
            }
        }

        // Primary Recommendation Banner Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("recommended_primary_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF10B981).copy(alpha = 0.15f)),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFF10B981))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFF10B981))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "#1 RECOMMENDED CHOICE: Backend Engineering / SDE",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF10B981)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "RATIONALE FOR B.SC GRADUATES: Backend engineering offers the highest density of high-paying off-campus job opportunities in India (Swiggy, Amazon, Zerodha, Fintechs, Startups). Production backend skill (Java + Spring Boot + System Design) combined with DSA proficiency completely eliminates degree barriers because employers urgently need engineers who can write real enterprise backend systems.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        // Filter Chips
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(
                    selected = selectedFilter == "All",
                    onClick = { selectedFilter = "All" },
                    label = { Text("All 10 Tech Paths") },
                    modifier = Modifier.testTag("filter_all_paths")
                )
                FilterChip(
                    selected = selectedFilter == "Top 3",
                    onClick = { selectedFilter = "Top 3" },
                    label = { Text("Top 3 Ranked") },
                    modifier = Modifier.testTag("filter_top3_paths")
                )
                FilterChip(
                    selected = selectedFilter == "Recommended",
                    onClick = { selectedFilter = "Recommended" },
                    label = { Text("Primary Recommendation") },
                    modifier = Modifier.testTag("filter_recommended_path")
                )
            }
        }

        // Career Path Cards List
        items(filteredList) { path ->
            CareerPathCard(path = path)
        }
    }
}

@Composable
fun CareerPathCard(path: CareerPathModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("career_card_${path.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (path.isRecommendedPrimary) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = if (path.isRecommendedPrimary) 2.dp else 1.dp,
            color = if (path.isRecommendedPrimary) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (path.ranking > 0) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = if (path.isTop3) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer
                        ) {
                            Text(
                                text = "#${path.ranking}",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (path.isTop3) Color.White else MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Text(
                        text = path.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                if (path.isRecommendedPrimary) {
                    AssistChip(
                        onClick = { },
                        label = { Text("Top Choice") },
                        leadingIcon = { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(14.dp)) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = path.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Metrics Grid
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                MetricRow(label = "Fresher Opportunities in India:", value = path.fresherOpps, isHighlight = path.fresherOpps == "High" || path.fresherOpps == "Very High (Startups & SMEs)")
                MetricRow(label = "Fresher Salary Range:", value = path.avgSalaryFresh, isHighlight = true)
                MetricRow(label = "Long-Term Salary Ceiling:", value = path.longTermSalary, isHighlight = true)
                MetricRow(label = "Suitability for B.Sc. Graduates:", value = path.bscSuitability, isHighlight = path.bscSuitability.startsWith("High"))
                MetricRow(label = "Importance of DSA:", value = path.dsaImportance)
                MetricRow(label = "Required Mathematics:", value = path.mathRequired)
                MetricRow(label = "AI / Automation Risk:", value = path.aiAutomationRisk, isHighlight = path.aiAutomationRisk == "Low")
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Summary Why
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = path.summaryWhy,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}

@Composable
fun MetricRow(label: String, value: String, isHighlight: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = if (isHighlight) FontWeight.Bold else FontWeight.Medium,
            color = if (isHighlight) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}
