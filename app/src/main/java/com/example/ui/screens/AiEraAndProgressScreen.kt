package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.sp
import com.example.data.DsaProgress
import com.example.data.MilestoneModel
import com.example.data.TaskProgress
import com.example.viewmodel.MainViewModel

@Composable
fun AiEraAndProgressScreen(
    viewModel: MainViewModel,
    taskProgressList: List<TaskProgress>,
    dsaProgressList: List<DsaProgress>
) {
    val score = viewModel.calculateScorecardScore(taskProgressList, dsaProgressList)
    val milestones = viewModel.getMilestones()
    val resources = viewModel.getResources()
    val thingsNotToLearn = viewModel.getThingsNotToLearnYet()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(bottom = 32.dp, top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Section 1: Monthly Scorecard Gauge Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("scorecard_gauge_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Monthly SDE Readiness Scorecard",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.surface,
                        shadowElevation = 4.dp,
                        modifier = Modifier.size(100.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "$score",
                                    style = MaterialTheme.typography.headlineLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = "/ 100",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = when {
                            score >= 85 -> "🔥 EXCELLENT: Ready for High-Paying SDE Product Interviews!"
                            score >= 60 -> "⚡ ON TRACK: Strong foundations built. Keep pushing projects and DSA!"
                            else -> "🌱 BEGINNING: Complete Month 1-3 tasks and solve DSA patterns to level up!"
                        },
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }

        // Section 2: AI-Era Strategy Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("ai_era_strategy_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Psychology, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Software Engineering in the AI Era",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "• Skills Gaining 3x Value: System Design, Database Indexing, Security, Distributed Architecture, Edge-Case Debugging, and AI API Integration.\n" +
                                "• Beginner Tasks Being Automated: Writing raw HTML/CSS boilerplate, basic syntax conversion, and simple CRUD code.\n" +
                                "• How to Use AI Tools Safely: Use GitHub Copilot / Cursor AI to speed up boilerplate and unit test generation, BUT ALWAYS dry-run logic manually and solve DSA without AI assistance!\n" +
                                "• AI Literacy Track: Learn how to connect Spring Boot to Gemini REST API and PGVector for RAG (Retrieval-Augmented Generation) semantic search.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        // Section 3: Progress Milestones (M3, M6, M9, M12, M18, M24)
        item {
            Text(
                text = "Progress Milestones Audit (M3 to M24)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        items(milestones) { milestone ->
            MilestoneCard(milestone = milestone)
        }

        // Section 4: What NOT to Learn Yet
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("things_not_to_learn_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEF4444).copy(alpha = 0.12f)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEF4444))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Cancel, contentDescription = null, tint = Color(0xFFEF4444))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Things You Should IGNORE / NOT Learn Yet",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFEF4444)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    thingsNotToLearn.forEach { item ->
                        Text(
                            text = "• $item",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }
                }
            }
        }

        // Section 5: Curated Free Resources Directory
        item {
            Text(
                text = "Curated High-Quality Free Resources",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        items(resources) { res ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(text = res.topic, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "🥇 Primary Free: ${res.primaryFree}", style = MaterialTheme.typography.bodySmall)
                    Text(text = "🥈 Backup Free: ${res.backupFree}", style = MaterialTheme.typography.bodySmall)
                    Text(text = "💻 Practice Platform: ${res.practicePlatform}", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
fun MilestoneCard(milestone: MilestoneModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("milestone_card_${milestone.monthLabel.replace(" ", "_")}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = milestone.monthLabel,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(10.dp))

            MilestoneSubSection(title = "🧠 KNOW:", items = milestone.know)
            MilestoneSubSection(title = "🔨 BUILD:", items = milestone.build)
            MilestoneSubSection(title = "🧩 SOLVE:", items = milestone.solve)
            MilestoneSubSection(title = "🗣️ EXPLAIN:", items = milestone.explain)
            MilestoneSubSection(title = "📄 RESUME HIGHLIGHT:", items = milestone.resumePoints)
        }
    }
}

@Composable
fun MilestoneSubSection(title: String, items: List<String>) {
    Column(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(text = title, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
        items.forEach { item ->
            Text(text = "  • $item", style = MaterialTheme.typography.bodySmall, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
