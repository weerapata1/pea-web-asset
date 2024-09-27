package com.PEA.webAsset.Entity;

import java.util.List;

public class PdfResponse {
    private Report report;
    private String path;

    // Getters and setters for 'path' and 'report'
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public static class Report {
        private int pageCount;
        private TemplateRepairReport templateRepairReport;
        private PdfProductionReport pdfProductionReport;
        private String id;
        private String timestamp;
        private String highestSeverity;

        // Getters and setters
        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public TemplateRepairReport getTemplateRepairReport() {
            return templateRepairReport;
        }

        public void setTemplateRepairReport(TemplateRepairReport templateRepairReport) {
            this.templateRepairReport = templateRepairReport;
        }

        public PdfProductionReport getPdfProductionReport() {
            return pdfProductionReport;
        }

        public void setPdfProductionReport(PdfProductionReport pdfProductionReport) {
            this.pdfProductionReport = pdfProductionReport;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getHighestSeverity() {
            return highestSeverity;
        }

        public void setHighestSeverity(String highestSeverity) {
            this.highestSeverity = highestSeverity;
        }
    }

    public static class TemplateRepairReport {
        private List<Entry> entries;

        // Getters and setters
        public List<Entry> getEntries() {
            return entries;
        }

        public void setEntries(List<Entry> entries) {
            this.entries = entries;
        }

        public static class Entry {
            private String message;
            private String severity;

            // Getters and setters
            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getSeverity() {
                return severity;
            }

            public void setSeverity(String severity) {
                this.severity = severity;
            }
        }
    }

    public static class PdfProductionReport {
        private List<Entry> entries;

        // Getters and setters
        public List<Entry> getEntries() {
            return entries;
        }

        public void setEntries(List<Entry> entries) {
            this.entries = entries;
        }

        public static class Entry {
            private String message;
            private String severity;

            // Getters and setters
            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getSeverity() {
                return severity;
            }

            public void setSeverity(String severity) {
                this.severity = severity;
            }
        }
    }
}
